package dev.jessefu.module_modify.model;

import java.util.Objects;

import dev.jessefu.component_base.base.BaseApplication;
import dev.jessefu.component_base.base.BaseModel;
import dev.jessefu.component_base.db.AccountEntityDao;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.component_base.util.RxTransformer;
import io.reactivex.Observable;

public class ModifyModel extends BaseModel {
    private static final String TAG = "ModifyModel";

    private AccountEntityDao mAccountEntityDao;

    public ModifyModel(){
        mAccountEntityDao = BaseApplication.getDaoSession().getAccountEntityDao();
    }

    /**插入或新增一条数据
     * 判断依据：entity.Id()是否为null
     * */
    public Observable<Boolean> insertOrUpdateEntity(AccountEntity entity){

       if (entity.getId() == null){//insert
            mAccountEntityDao.insert(entity);
       }else{//update
            mAccountEntityDao.update(entity);
       }

       return Observable.just(true)
               .compose(RxTransformer.switchSchedulers());

    }
}
