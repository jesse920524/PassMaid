package dev.jessefu.module_main.biz.category.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import dev.jessefu.component_base.base.BaseFragment;
import dev.jessefu.component_base.event.InitTabEvent;
import dev.jessefu.module_main.R;
import dev.jessefu.module_main.R2;
import dev.jessefu.module_main.adapter.CategoryPagerAdapter;

public class CategoryFragment extends BaseFragment {
    private static final String TAG = "CategoryFragment";

    public static CategoryFragment newInstance(String arg){
        Bundle bundle = new Bundle();
        bundle.putString(BaseFragment.FRAGMENT_ARGUMENT, arg);
        CategoryFragment instance = new CategoryFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @BindView(R2.id.vp_category)
    ViewPager mViewPager;

    private CategoryPagerAdapter mViewPagerAdapter;

    @Override
    protected int provideLayoutRes() {
        return R.layout.main_fragment_category;
    }

    @Override
    protected void initViews(View view) {
        mViewPagerAdapter = new CategoryPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    @Override
    protected void initViewModel() {
        //no vm
    }

    @Override
    protected void initData() {
        EventBus.getDefault().postSticky(InitTabEvent.newInstance(""));
    }

    public ViewPager getViewPager(){
        return mViewPager;
    }
}
