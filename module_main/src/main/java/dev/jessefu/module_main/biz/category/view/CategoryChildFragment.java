package dev.jessefu.module_main.biz.category.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import dev.jessefu.component_base.base.BaseFragment;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.component_base.event.RefreshDataEvent;
import dev.jessefu.component_base.router.Router;
import dev.jessefu.module_main.R;
import dev.jessefu.module_main.R2;
import dev.jessefu.module_main.adapter.RvStarAdapter;
import dev.jessefu.module_main.biz.category.vm.CategoryChildVM;

/**
 * Category子Fragment*/
public class CategoryChildFragment extends BaseFragment{
    private static final String TAG = "CategoryChildFragment";

    private static final String CATEGORY = "category";
    public static CategoryChildFragment newInstance(String category){
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY, category);
        CategoryChildFragment fragment = new CategoryChildFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R2.id.rv_cc)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private RvStarAdapter mAdapter;

    private CategoryChildVM mViewModel;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int provideLayoutRes() {
        return R.layout.main_fragment_category_child;
    }

    @Override
    protected void initViews(View view) {
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new RvStarAdapter();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AccountEntity entity = (AccountEntity) adapter.getData().get(position);
                Router.INSTANCE.toDetailsActivity(entity);
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(CategoryChildVM.class);
        mViewModel.setCategory(getArguments().getString(CATEGORY));
    }

    @Override
    protected void initData() {
        mViewModel.start();
        mViewModel.getLiveDataAccountEntities().observe(this, new Observer<List<AccountEntity>>() {
            @Override
            public void onChanged(@Nullable List<AccountEntity> accountEntities) {
                mAdapter.setNewData(accountEntities);
            }
        });
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEventRefreshData(RefreshDataEvent event){
        Log.d(TAG, "onEventRefreshData: exec");
        mViewModel.start();
    }
}
