package dev.jessefu.module_main.biz.category.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.jessefu.component_base.base.BaseFragment;
import dev.jessefu.module_main.R;

public class CategoryFragment extends BaseFragment {
    private static final String TAG = "CategoryFragment";

    public static CategoryFragment newInstance(String arg){
        Bundle bundle = new Bundle();
        bundle.putString(BaseFragment.FRAGMENT_ARGUMENT, arg);
        CategoryFragment instance = new CategoryFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    protected int provideLayoutRes() {
        return R.layout.main_fragment_category;
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initData() {

    }
}
