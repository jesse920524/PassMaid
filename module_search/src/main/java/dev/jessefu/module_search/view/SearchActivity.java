package dev.jessefu.module_search.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;

import dev.jessefu.component_base.base.BaseActivity;
import dev.jessefu.component_base.router.RouterConstants;
import dev.jessefu.module_search.R;
import dev.jessefu.module_search.vm.SearchVM;

@Route(path = RouterConstants.ModuleSearch.ACTIVITY_SEARCH)
public class SearchActivity extends BaseActivity {
    private static final String TAG = "SearchActivity";

    private SearchVM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(SearchVM.class);
    }
}
