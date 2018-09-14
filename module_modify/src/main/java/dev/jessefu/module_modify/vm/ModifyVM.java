package dev.jessefu.module_modify.vm;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import dev.jessefu.component_base.base.BaseViewModel;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.component_base.router.Router;
import dev.jessefu.module_modify.model.ModifyModel;
import io.reactivex.functions.Consumer;

public class ModifyVM extends BaseViewModel<ModifyModel> {
    private static final String TAG = "ModifyVM";

    private MutableLiveData<String> liveDataTitle;
    private MutableLiveData<String> liveDataAccount;
    private MutableLiveData<String> liveDataPwd;
    private MutableLiveData<String> liveDataDesc;

    private MutableLiveData<Boolean> liveDataFinish;//调用view.finish()

    public MutableLiveData<Boolean> getLiveDataFinish() {
        return liveDataFinish;
    }

    public ModifyVM(){
        model = new ModifyModel();
        liveDataFinish = new MutableLiveData<>();
        liveDataFinish.setValue(false);
    }
    @Override
    public void start() {

    }

    public boolean checkValidate(){
        // TODO: 2018-09-14 验证数据格式合法
        return true;
    }

    /**提交了修改数据的请求,检验数据格式合法性后,更新db*/
    public void submitData(@NonNull String title,
                           @NonNull String account,
                           String pwd,
                           String desc,
                           boolean isChecked,
                           @NonNull String category){
        checkValidate();
        AccountEntity entity = new AccountEntity();
        entity.setTitle(title);
        entity.setAccount(account);
        entity.setPassword(pwd);
        entity.setDescription(desc);
        entity.setIsStar(isChecked);
        entity.setCategory(category);

        insertOrUpdateEntity(entity);

    }
    public void insertOrUpdateEntity(AccountEntity entity){
        model.insertOrUpdateEntity(entity)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        liveDataFinish.setValue(true);
                        Log.d(TAG, "accept: " + entity);
                    }
                });
//        Router.INSTANCE.toMainActivity();
    }


}
