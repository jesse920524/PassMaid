package dev.jessefu.module_details.details.view;

import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import dev.jessefu.component_base.base.BaseActivity;
import dev.jessefu.component_base.router.RouterConstants;
import dev.jessefu.module_details.R;
@Route(path = RouterConstants.ModuleDetails.ACTIVITY_DETAILS)
public class DetailsActivity extends BaseActivity {
    private static final String TAG = "DetailsActivity";

    @Autowired(name = RouterConstants.ModuleDetails.ACTIVITY_DETAIL_BUNDLE_KEY)
    String json;

    @Override
    protected int getLayoutRes() {
        return R.layout.details_activity_details;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        Log.d(TAG, "initData: "  + json);
    }

    @Override
    protected void initViewModel() {

    }
}
