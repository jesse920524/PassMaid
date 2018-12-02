package dev.jessefu.module_main.biz.category.child.model;

import java.util.List;

import dev.jessefu.component_base.base.BaseApp;
import dev.jessefu.component_base.base.BaseModel;
import dev.jessefu.component_base.db.AccountEntityDao;
import dev.jessefu.component_base.db.entity.AccountEntity;
import io.reactivex.Observable;

public class CategoryChildModel extends BaseModel {
    private static final String TAG = "CategoryChildModel";

    private AccountEntityDao mAccountEntityDao;

    public CategoryChildModel(){
        mAccountEntityDao = BaseApp.getDaoSession().getAccountEntityDao();
    }

    /**从db读取指定category的数据
     * @param category 类型*/
    public Observable<List<AccountEntity>> queryDataFromDB(String category){
        List<AccountEntity> list;
        if (category.equals("全部")){
            list = mAccountEntityDao.queryBuilder()
                    .list();
        }else{
            list = mAccountEntityDao.queryBuilder()
                    .where(AccountEntityDao.Properties.Category.eq(category))
                    .list();
        }

        return Observable.just(list);
    }
}
