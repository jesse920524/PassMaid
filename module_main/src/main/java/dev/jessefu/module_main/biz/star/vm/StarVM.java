package dev.jessefu.module_main.biz.star.vm;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import dev.jessefu.component_base.base.BaseViewModel;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.module_main.biz.star.model.StarModel;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class StarVM extends BaseViewModel<StarModel>{
    private static final String TAG = "StarVM";

    private MutableLiveData<List<AccountEntity>> liveDataAccountEntities;

    public StarVM(){
        model = new StarModel();
        liveDataAccountEntities = new MutableLiveData<>();
    }

    public MutableLiveData<List<AccountEntity>> getLiveDataAccountEntities() {
        return liveDataAccountEntities;
    }

    @Override
    public void start() {
        initTestData();
        getAccountEntitiesFromDB();
    }

    public void getAccountEntitiesFromDB(){
        model.getEntityList()
                .subscribe(new AccountEntitiesObserver());
    }

    public void initTestData(){
        model.initTestData();
    }

    public void clearTestData(){
        model.clearTestData();
    }

    private class AccountEntitiesObserver implements Observer<List<AccountEntity>>{

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(List<AccountEntity> data) {
            Log.d(TAG, "onNext: " + data);
            liveDataAccountEntities.setValue(data);
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError: " + e.getLocalizedMessage());
        }

        @Override
        public void onComplete() {

        }
    }



}
