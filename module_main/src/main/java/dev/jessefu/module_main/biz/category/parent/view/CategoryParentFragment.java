package dev.jessefu.module_main.biz.category.parent.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import dev.jessefu.component_base.base.BaseFragment;
import dev.jessefu.component_base.db.entity.CategoryEntity;
import dev.jessefu.component_base.event.InitTabEvent;
import dev.jessefu.module_main.R;
import dev.jessefu.module_main.R2;
import dev.jessefu.module_main.adapter.CategoryPagerAdapter;
import dev.jessefu.module_main.biz.category.parent.vm.CategoryParentVM;

public class CategoryParentFragment extends BaseFragment {
    private static final String TAG = "CategoryParentFragment";

    public static CategoryParentFragment newInstance(String arg){
        Bundle bundle = new Bundle();
        bundle.putString(BaseFragment.FRAGMENT_ARGUMENT, arg);
        CategoryParentFragment instance = new CategoryParentFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @BindView(R2.id.vp_category)
    ViewPager mViewPager;

    private CategoryParentVM mViewModel;

    private CategoryPagerAdapter mViewPagerAdapter;

    @Override
    protected int provideLayoutRes() {
        return R.layout.main_fragment_category;
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(CategoryParentVM.class);

    }

    @Override
    protected void initData() {
        mViewModel.start();
        mViewModel.getLiveDataCategoryList().observe(this, new Observer<List<CategoryEntity>>() {
            @Override
            public void onChanged(@Nullable List<CategoryEntity> categoryEntities) {
                mViewPagerAdapter = new CategoryPagerAdapter(getActivity().getSupportFragmentManager(), categoryEntities);
                mViewPager.setAdapter(mViewPagerAdapter);
            }
        });
        EventBus.getDefault().postSticky(InitTabEvent.newInstance(""));
    }

    public ViewPager getViewPager(){
        return mViewPager;
    }
}
