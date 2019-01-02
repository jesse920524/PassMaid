package dev.jessefu.module_search.vm;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import dev.jessefu.component_base.base.BaseViewModel;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.module_search.model.SearchModel;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SearchVM extends BaseViewModel<SearchModel> {
    private static final String TAG = "SearchVM";

    private SearchModel model;

    private MutableLiveData<List<AccountEntity>> liveDataResult;

    public SearchVM(){
        model = new SearchModel();
        liveDataResult = new MutableLiveData<>();
    }

    @Override
    public void start() {
        //
    }

    public void query(final @NonNull String keyword){

                model.query(keyword)
                        .subscribe(new Consumer<List<AccountEntity>>() {
                    @Override
                    public void accept(List<AccountEntity> accountEntities) throws Exception {
                        Log.d(TAG, "accept: " + accountEntities);
                        liveDataResult.setValue(accountEntities);
                    }
                });
    }

    public MutableLiveData<List<AccountEntity>> getLiveDataResult() {
        return liveDataResult;
    }
}
