package dev.jessefu.module_main.biz.category.choose.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import dev.jessefu.component_base.base.BaseActivity;
import dev.jessefu.component_base.db.entity.CategoryEntity;
import dev.jessefu.module_main.R;
import dev.jessefu.module_main.R2;
import dev.jessefu.module_main.biz.category.choose.view.adapter.RvCategoryChooseAdapter;
import dev.jessefu.module_main.biz.category.choose.vm.CategoryChooseVM;


public class CategoryChooseActivity extends BaseActivity {
    private static final String TAG = "CategoryChooseActivity";

    @BindView(R2.id.iv_acc_back)
    ImageView mIvBack;
    @BindView(R2.id.rv_acc)
    RecyclerView mRecyclerView;
    @BindView(R2.id.fab_acc)
    FloatingActionButton mFAB;

    private CategoryChooseVM mViewModel;
    private RecyclerView.LayoutManager layoutManager;
    private RvCategoryChooseAdapter mAdapter;


    @Override
    protected int getLayoutRes() {
        return R.layout.main_activity_category_choose;
    }

    @Override
    protected void initViews() {
        layoutManager = new GridLayoutManager(this, 2);
        mAdapter = new RvCategoryChooseAdapter();
        mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                // TODO: 2018-12-05 alert : modify name || delete

                return false;
            }
        });
        mRecyclerView.setLayoutManager(layoutManager);

        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018-12-05 show alert new category
            }
        });
    }

    @Override
    protected void initData() {
        mViewModel.start();
        mViewModel.getLiveDataCategoryList().observe(this, new Observer<List<CategoryEntity>>() {
            @Override
            public void onChanged(@Nullable List<CategoryEntity> categoryEntities) {
                mAdapter.setNewData(categoryEntities);
            }
        });
    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(CategoryChooseVM.class);
    }
}
