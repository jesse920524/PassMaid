package dev.jessefu.module_entrance.entrance.model;

import dev.jessefu.component_base.base.BaseApp;
import dev.jessefu.component_base.base.BaseModel;
import dev.jessefu.component_base.db.CategoryEntityDao;
import dev.jessefu.component_base.db.entity.CategoryEntity;
import dev.jessefu.component_base.enums.DefaultCategory;
import dev.jessefu.component_base.sp.SpConstants;
import dev.jessefu.component_base.util.SPUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class EntranceModel extends BaseModel {
    private static final String TAG = "EntranceModel";

    private CategoryEntityDao categoryEntityDao;

    public EntranceModel(){
        categoryEntityDao = BaseApp.getDaoSession().getCategoryEntityDao();
    }

    /**@return true if user first time open the app
     * false otherwise
     * */
    public boolean isFirstUse(){

        return !SPUtils.getInstance(SpConstants.ModuleBase.SP_FIRST_USE)
                .getBoolean(SpConstants.ModuleBase.SP_FIRST_USE_KEY);
    }

    public void handleFirstUse(){
        SPUtils.getInstance(SpConstants.ModuleBase.SP_FIRST_USE)
                .put(SpConstants.ModuleBase.SP_FIRST_USE_KEY, true);
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
