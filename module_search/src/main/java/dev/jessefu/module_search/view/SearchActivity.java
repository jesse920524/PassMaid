package dev.jessefu.module_search.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;

import dev.jessefu.component_base.base.BaseActivity;
import dev.jessefu.component_base.router.RouterConstants;
import dev.jessefu.module_search.R;

@Route(path = RouterConstants.ModuleSearch.ACTIVITY_SEARCH)
public class SearchActivity extends BaseActivity {
    private static final String TAG = "SearchActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.search_activity_search);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.search_activity_search;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }
}
