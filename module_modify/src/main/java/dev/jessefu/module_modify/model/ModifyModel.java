package dev.jessefu.module_modify.model;

import dev.jessefu.component_base.base.BaseApp;
import dev.jessefu.component_base.base.BaseModel;
import dev.jessefu.component_base.db.AccountEntityDao;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.component_base.util.RxTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class ModifyModel extends BaseModel {
    private static final String TAG = "ModifyModel";

    private AccountEntityDao mAccountEntityDao;

    public ModifyModel(){
        mAccountEntityDao = BaseApp.getDaoSession().getAccountEntityDao();
    }

    /**插入或新增一条数据
     * 判断依据：entity.Id()是否为null
     * */
    public Observable<Boolean> insertOrUpdateEntity(AccountEntity entity){
        mAccountEntityDao.insertOrReplace(entity);

       return Observable.just(true)
               .compose(RxTransformer.switchSchedulers());
    }

    /**删除一条数据*/
    public Observable<Boolean> deleteEntity(AccountEntity entity){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                mAccountEntityDao.delete(entity);
                emitter.onNext(true);
                emitter.onComplete();
            }
        }).compose(RxTransformer.switchSchedulers());
    }
}
