package dev.jessefu.module_main.biz.category.choose.model;

import android.util.Log;

import java.util.List;

import dev.jessefu.component_base.base.BaseApp;
import dev.jessefu.component_base.base.BaseModel;
import dev.jessefu.component_base.db.CategoryEntityDao;
import dev.jessefu.component_base.db.entity.CategoryEntity;
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

    public CategoryChooseModel(){
        categoryEntityDao = BaseApp.getDaoSession().getCategoryEntityDao();
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
    public Observable<Boolean> checkDuplicate(String name){
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
    public void insert(final String name){
        Observable.create(new ObservableOnSubscribe<String>() {
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
                      categoryEntityDao.insert(new CategoryEntity());
                  }
              }).compose(RxTransformer.switchSchedulers())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "accept: " + name);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "accept: " + throwable.getLocalizedMessage());
                        ToastUtil.showShort(throwable.getLocalizedMessage());
                    }
                });
    }
    
    
}
