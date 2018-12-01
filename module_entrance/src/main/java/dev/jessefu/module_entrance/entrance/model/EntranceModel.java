package dev.jessefu.module_entrance.entrance.model;

import android.database.DefaultDatabaseErrorHandler;
import android.util.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import dev.jessefu.component_base.base.BaseApplication;
import dev.jessefu.component_base.base.BaseModel;
import dev.jessefu.component_base.db.CategoryEntityDao;
import dev.jessefu.component_base.db.DaoMaster;
import dev.jessefu.component_base.db.DaoSession;
import dev.jessefu.component_base.db.entity.CategoryEntity;
import dev.jessefu.component_base.enums.DefaultCategory;
import dev.jessefu.component_base.util.RxTransformer;
import dev.jessefu.component_base.util.SPUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class EntranceModel extends BaseModel {
    private static final String TAG = "EntranceModel";

    private CategoryEntityDao categoryEntityDao;

    public EntranceModel(){
        categoryEntityDao = BaseApplication.getDaoSession().getCategoryEntityDao();
    }

    /**@return true if user first time open the app
     * false otherwise
     * */
    public boolean isFirstUse(){
        return false;
    }

    /**init default account categories.
     *
     * called when user first time open the app,
     *
     * */
    public Observable<Boolean> initDefCategories(){

       return Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                categoryEntityDao.deleteAll();
                emitter.onNext("");
            }
        }).subscribeOn(Schedulers.io())
           .flatMap(new Function<Object, ObservableSource<Boolean>>() {
               @Override
               public ObservableSource<Boolean> apply(Object o) throws Exception {
                   for (DefaultCategory c :
                           DefaultCategory.values()) {
                       CategoryEntity entity = new CategoryEntity();
                       entity.setName(c.getName());
                       categoryEntityDao.insert(entity);
                   }
                   return Observable.just(true);
               }
           }).subscribeOn(Schedulers.io());

    }
}
