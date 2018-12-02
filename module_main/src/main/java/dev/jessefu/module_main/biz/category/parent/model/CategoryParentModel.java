package dev.jessefu.module_main.biz.category.parent.model;

import java.util.List;

import dev.jessefu.component_base.base.BaseApp;
import dev.jessefu.component_base.base.BaseModel;
import dev.jessefu.component_base.db.CategoryEntityDao;
import dev.jessefu.component_base.db.entity.CategoryEntity;
import dev.jessefu.component_base.util.RxTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class CategoryParentModel extends BaseModel {
    private static final String TAG = "CategoryParentModel";

    private CategoryEntityDao categoryEntityDao;

    public CategoryParentModel(){
        categoryEntityDao = BaseApp.getDaoSession().getCategoryEntityDao();
    }

    /**get categories */
    public Observable<List<CategoryEntity>> getCategoryList(){
        return Observable.create(new ObservableOnSubscribe<List<CategoryEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<CategoryEntity>> emitter) throws Exception {

                List<CategoryEntity> list = categoryEntityDao.queryBuilder()
                        .build()
                        .list();

                emitter.onNext(list);
            }
        }).compose(RxTransformer.switchSchedulers());

    }
}
