package dev.jessefu.module_main.biz.category.choose.model;

import android.util.Log;

import java.util.List;

import dev.jessefu.component_base.base.BaseApp;
import dev.jessefu.component_base.base.BaseModel;
import dev.jessefu.component_base.db.AccountEntityDao;
import dev.jessefu.component_base.db.CategoryEntityDao;
import dev.jessefu.component_base.db.DaoSession;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.component_base.db.entity.CategoryEntity;
import dev.jessefu.component_base.enums.DefaultCategory;
import dev.jessefu.component_base.util.RxTransformer;
import dev.jessefu.component_base.util.ToastUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CategoryChooseModel extends BaseModel {
    private static final String TAG = "CategoryChooseModel";

    private CategoryEntityDao categoryEntityDao;
    private AccountEntityDao accountEntityDao;
    public CategoryChooseModel(){
        DaoSession daoSession = BaseApp.getDaoSession();
        categoryEntityDao = daoSession.getCategoryEntityDao();
        accountEntityDao = daoSession.getAccountEntityDao();
    }

    /**read category list from db*/
    public Observable<List<CategoryEntity>> getCategoryList(){
        return Observable.create(new ObservableOnSubscribe<List<CategoryEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<CategoryEntity>> emitter) throws Exception {
                List<CategoryEntity> list = categoryEntityDao.queryBuilder()
                        .list();
                emitter.onNext(list);
            }
        }).compose(RxTransformer.switchSchedulers());
    }

    /**check wether category name already exists in the db
     *
     * @param name the name of category
     *
     * @return true : no duplicate row in the db
     *      false : duplicate row exists.
     */
    private Observable<Boolean> checkDuplicate(String name){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                int size = categoryEntityDao.queryBuilder().where(CategoryEntityDao.Properties.Name.eq(name))
                        .list().size();
                if (size == 0){
                    emitter.onNext(true);
                }else{
                    emitter.onError(new Exception("category name duplicate!"));
                }
            }
        }).compose(RxTransformer.switchSchedulers());

    }
    
    /** insert one row in the db
     * 
     * 1st: checkDuplicate 
     * 2nd: insert into db
     * 
     * */
    public Observable<String> insert(final String name){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                int size = categoryEntityDao.queryBuilder().where(CategoryEntityDao.Properties.Name.eq(name))
                        .list().size();
                if (size == 0){
                    emitter.onNext(name);
                }else{
                    emitter.onError(new Exception("category name duplicate!"));
                }
            }
        }).subscribeOn(Schedulers.io())
              .doOnNext(new Consumer<String>() {
                  @Override
                  public void accept(String s) throws Exception {
                      CategoryEntity categoryEntity = new CategoryEntity();
                      categoryEntity.setName(s);
                      categoryEntityDao.insert(categoryEntity);
                  }
              }).compose(RxTransformer.switchSchedulers());

    }

    /**delete a category by category name
     * @param categoryName
     *
     * @return true if delete successfully, false otherwise. */
    public Observable<Boolean> deleteCategory(String categoryName){
        // 2018-12-11 1st: name exists. 2nd: items -> "other' 3rd: delete

        return Observable.just(categoryName)
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        CategoryEntity data = categoryEntityDao.queryBuilder()
                                .where(CategoryEntityDao.Properties.Name.eq(s))
                                .uniqueOrThrow();
                        return Observable.just(data.getName());
                    }
                }).compose(RxTransformer.switchSchedulers())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        List<AccountEntity> list = accountEntityDao.queryBuilder()
                                .where(AccountEntityDao.Properties.Category.eq(s))
                                .list();

                        if (list.size() > 0){
                            for (AccountEntity entity :
                                    list) {
                                entity.setCategory(DefaultCategory.OTHER.getName());
                                accountEntityDao.save(entity);
                            }
                        }
                    }
                }).map(new Function<String, Boolean>() {
            @Override
            public Boolean apply(String s) throws Exception {
                CategoryEntity categoryEntity = categoryEntityDao.queryBuilder()
                        .where(CategoryEntityDao.Properties.Name.eq(s)).uniqueOrThrow();
                categoryEntityDao.delete(categoryEntity);
                return true;
            }
        });
    }
    
    
}
