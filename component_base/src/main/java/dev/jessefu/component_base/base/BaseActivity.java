package dev.jessefu.component_base.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import butterknife.ButterKnife;
import dev.jessefu.component_base.router.Router;
import dev.jessefu.component_base.util.ToastUtil;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutRes());

        ButterKnife.bind(this);
        Router.INSTANCE.inject(this);
        initViews();
        initViewModel();
        initData();
    }

    /**provide layout res*/
    protected abstract @LayoutRes int getLayoutRes();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ToastUtil.cancelToast();
    }

    /**初始化Activity视图*/
    protected abstract void initViews();

    /**初始化Activity数据(request data from viewModel.)*/
    protected abstract void initData();

    /**初始化viewModel*/
    protected abstract void initViewModel();
}
