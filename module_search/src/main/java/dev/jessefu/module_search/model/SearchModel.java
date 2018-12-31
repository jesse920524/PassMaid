package dev.jessefu.module_search.model;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import dev.jessefu.component_base.base.BaseApp;
import dev.jessefu.component_base.base.BaseModel;
import dev.jessefu.component_base.db.AccountEntityDao;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.component_base.util.RxTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;

public class SearchModel extends BaseModel {
    private static final String TAG = "SearchModel";

    private static final String SIGNAL = "%";
    private AccountEntityDao mAccountEntityDao;
    public SearchModel(){
        mAccountEntityDao = BaseApp.getDaoSession().getAccountEntityDao();
    }

    public Observable<List<AccountEntity>> query(@NonNull final String keyword){

        Observable<List<AccountEntity>> observableTitle = Observable.create(new ObservableOnSubscribe<List<AccountEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AccountEntity>> emitter) throws Exception {

            }
        });

        Observable<List<AccountEntity>> observableAccount = Observable.create(new ObservableOnSubscribe<List<AccountEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AccountEntity>> emitter) throws Exception {

            }
        });

        Observable<List<AccountEntity>> observableDesc = Observable.create(new ObservableOnSubscribe<List<AccountEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AccountEntity>> emitter) throws Exception {

            }
        });

        Observable.merge(observableAccount, observableDesc, observableTitle);


        return Observable.create(new ObservableOnSubscribe<List<AccountEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AccountEntity>> emitter) throws Exception {
                List<AccountEntity> list = mAccountEntityDao.queryBuilder()
                        .where(
                                AccountEntityDao.Properties.Title.like(SIGNAL + keyword + SIGNAL)
                                )
//                AccountEntityDao.Properties.Category.like(SIGNAL + keyword + SIGNAL),
//                AccountEntityDao.Properties.Description.like(SIGNAL + keyword + SIGNAL)
                        .list();
                Log.d(TAG, "subscribe: " + list);
                emitter.onNext(list);
            }
        }).compose(RxTransformer.switchSchedulers());
    }
}
