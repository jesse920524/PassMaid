package dev.jessefu.module_main.biz.category.choose.view;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import butterknife.BindView;
import dev.jessefu.component_base.base.BaseActivity;
import dev.jessefu.module_main.R;
import dev.jessefu.module_main.R2;
import dev.jessefu.module_main.biz.category.choose.view.adapter.RvCategoryChooseAdapter;


public class CategoryChooseActivity extends BaseActivity {
    private static final String TAG = "CategoryChooseActivity";

    @BindView(R2.id.iv_acc_back)
    ImageView mIvBack;
    @BindView(R2.id.rv_acc)
    RecyclerView mRecyclerView;
    @BindView(R2.id.fab_acc)
    FloatingActionButton mFAB;

    private RecyclerView.LayoutManager layoutManager;
    private RvCategoryChooseAdapter mAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.main_activity_category_choose;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViewModel() {

    }
}
