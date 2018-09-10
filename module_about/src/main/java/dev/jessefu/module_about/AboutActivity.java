package dev.jessefu.module_about;

import com.alibaba.android.arouter.facade.annotation.Route;

import dev.jessefu.component_base.base.BaseActivity;
import dev.jessefu.component_base.router.RouterConstants;

@Route(path = RouterConstants.ModuleAbout.ACTIVITY_ABOUT)
public class AboutActivity extends BaseActivity {
    private static final String TAG = "AboutActivity";

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }
}
