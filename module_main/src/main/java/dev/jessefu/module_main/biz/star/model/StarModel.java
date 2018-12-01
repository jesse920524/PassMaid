package dev.jessefu.module_main.biz.star.model;

import java.util.List;

import dev.jessefu.component_base.base.BaseApplication;
import dev.jessefu.component_base.base.BaseModel;
import dev.jessefu.component_base.db.AccountEntityDao;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.component_base.enums.DefaultCategory;
import dev.jessefu.component_base.util.RxTransformer;
import io.reactivex.Observable;

public class StarModel extends BaseModel implements IStarModel{
    private static final String TAG = "StarModel";

    private AccountEntityDao mAccountEntityDao;

    public StarModel(){
        super();
        mAccountEntityDao = BaseApplication.getDaoSession().getAccountEntityDao();
    }
    @Override
    public Observable<List<AccountEntity>> getEntityList() {
        List<AccountEntity> list = mAccountEntityDao.queryBuilder()
                .where(AccountEntityDao.Properties.IsStar.eq(true))
                .list();

        return Observable.just(list)
                .compose(RxTransformer.switchSchedulers());
    }

    @Override
    public void initTestData() {
        if (mAccountEntityDao.queryBuilder().list().isEmpty()){
            AccountEntity entity1 = new AccountEntity();
            entity1.setTitle("qq");
            entity1.setAccount("276883319");
            entity1.setDescription("my qq number");
            entity1.setPassword("jesse");
            entity1.setIsStar(true);
            entity1.setCategory(DefaultCategory.SOCIAL.getName());

            AccountEntity entity2 = new AccountEntity();
            entity2.setTitle("坚果云");
            entity2.setAccount("jesse920524@163.com");
            entity2.setDescription("description");
            entity2.setPassword("jesse dev2017");
            entity2.setIsStar(true);
            entity2.setCategory(DefaultCategory.SOCIAL.getName());

            mAccountEntityDao.insertInTx(entity1, entity2);
        }

    }

    @Override
    public void clearTestData() {
        mAccountEntityDao.deleteAll();
    }
}
