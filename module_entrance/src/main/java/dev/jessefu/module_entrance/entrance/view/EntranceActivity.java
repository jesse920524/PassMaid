package dev.jessefu.module_entrance.entrance.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.jessefu.component_base.base.BaseActivity;
import dev.jessefu.component_base.router.Router;
import dev.jessefu.component_base.router.RouterConstants;
import dev.jessefu.module_entrance.R;
import dev.jessefu.module_entrance.R2;
import dev.jessefu.module_entrance.entrance.vm.EntranceVM;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@Route(path = RouterConstants.ModuleEntrance.ACTIVITY_ENTRANCE)
public class EntranceActivity extends BaseActivity{
    private static final String TAG = "EntranceActivity";

    private EntranceVM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.entrance_activity_entrance;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        mViewModel.start();
        mViewModel.getLiveDataInit().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                toMainActivity();
            }
        });
    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(EntranceVM.class);
    }

    private void toMainActivity() {
        Observable.timer(1500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Router.INSTANCE.toMainActivity();
                        finish();
                    }
                });
    }
}
