package dev.jessefu.module_main.biz.category.choose.model;

import java.util.List;

import dev.jessefu.component_base.base.BaseApp;
import dev.jessefu.component_base.base.BaseModel;
import dev.jessefu.component_base.db.CategoryEntityDao;
import dev.jessefu.component_base.db.entity.CategoryEntity;
import dev.jessefu.component_base.util.RxTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

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
}
