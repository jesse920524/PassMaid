package dev.jessefu.module_search.model;

import android.support.annotation.NonNull;
import android.util.Log;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import dev.jessefu.component_base.base.BaseApp;
import dev.jessefu.component_base.base.BaseModel;
import dev.jessefu.component_base.db.AccountEntityDao;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.component_base.util.RxTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SearchModel extends BaseModel {
    private static final String TAG = "SearchModel";

    private static final String SIGNAL = "%";
    private AccountEntityDao mAccountEntityDao;
    public SearchModel(){
        mAccountEntityDao = BaseApp.getDaoSession().getAccountEntityDao();
    }

    public Observable<List<AccountEntity>> query(@NonNull final String keyword){
        QueryBuilder queryBuilder = mAccountEntityDao.queryBuilder();

        return Observable.create(new ObservableOnSubscribe<List<AccountEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AccountEntity>> emitter) throws Exception {
                List<AccountEntity> list = mAccountEntityDao.queryBuilder()
                        .whereOr(AccountEntityDao.Properties.Account.like(SIGNAL + keyword + SIGNAL),
                                AccountEntityDao.Properties.Title.like(SIGNAL + keyword + SIGNAL),
                                AccountEntityDao.Properties.Description.like(SIGNAL + keyword + SIGNAL))
                        .list();
                emitter.onNext(list);
            }
        });






    }
}
