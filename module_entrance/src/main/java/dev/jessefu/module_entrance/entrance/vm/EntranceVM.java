package dev.jessefu.module_entrance.entrance.vm;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import dev.jessefu.component_base.base.BaseViewModel;
import dev.jessefu.module_entrance.entrance.model.EntranceModel;
import io.reactivex.functions.Consumer;

public class EntranceVM extends BaseViewModel<EntranceModel> {
    private static final String TAG = "EntranceVM";

    private EntranceModel model;

    private MutableLiveData<Boolean> liveDataInit;

    public EntranceVM(){
        model = new EntranceModel();
        liveDataInit = new MutableLiveData<>();
    }

    @Override
    public void start() {
        if (model.isFirstUse()){
            model.initDefCategories().subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean aBoolean) throws Exception {
                    model.handleFirstUse();
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    Log.d(TAG, "accept: " + throwable.getLocalizedMessage());
                }
            });
        }

        liveDataInit.setValue(true);
    }

    public MutableLiveData<Boolean> getLiveDataInit() {
        return liveDataInit;
    }
}
