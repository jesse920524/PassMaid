package dev.jessefu.module_main.biz.star.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import dev.jessefu.component_base.base.BaseFragment;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.component_base.router.Router;
import dev.jessefu.module_main.R;
import dev.jessefu.module_main.R2;
import dev.jessefu.module_main.adapter.RvStarAdapter;
import dev.jessefu.module_main.biz.star.vm.StarVM;

public class StarFragment extends BaseFragment {
    private static final String TAG = "StarFragment";

    public static StarFragment newInstance(String arg){
        Bundle bundle = new Bundle();
        bundle.putString(BaseFragment.FRAGMENT_ARGUMENT, arg);
        StarFragment instance = new StarFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @BindView(R2.id.rv_main_star)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private RvStarAdapter mAdapter;

    private StarVM mViewModel;

    @Override
    protected int provideLayoutRes() {
        return R.layout.main_fragment_star;
    }

    @Override
    protected void initViews(View view) {
        //init Rv
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new RvStarAdapter();
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AccountEntity accountEntity = (AccountEntity) adapter.getData().get(position);
                Router.INSTANCE.toDetailsActivity(accountEntity);
            }
        });
    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(StarVM.class);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mViewModel.clearTestData();
    }
}
