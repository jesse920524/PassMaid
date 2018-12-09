package dev.jessefu.module_main.biz.category.choose.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import dev.jessefu.component_base.base.BaseActivity;
import dev.jessefu.component_base.db.entity.CategoryEntity;
import dev.jessefu.component_base.event.AddCategoryEvent;
import dev.jessefu.component_base.router.RouterConstants;
import dev.jessefu.component_base.util.RxTransformer;
import dev.jessefu.component_base.util.ToastUtil;
import dev.jessefu.module_main.R;
import dev.jessefu.module_main.R2;
import dev.jessefu.module_main.adapter.RvCategoryChooseAdapter;
import dev.jessefu.module_main.biz.category.choose.vm.CategoryChooseVM;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

@Route(path= RouterConstants.ModuleMain.ACTIVITY_CHOOSE_CATEGORY)
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
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

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
        mRecyclerView.setAdapter(mAdapter);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCategory();
            }
        });
    }

    private void addCategory() {
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                if (mAdapter.getData().size()>= 20){
                    throw new IllegalStateException("分类数量已达到上限, 无法新建分类!");
                }

                emitter.onNext(true);
            }
        }).compose(RxTransformer.switchSchedulers())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        AddCategoryDialog.newInstance().show(getSupportFragmentManager(), "");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(CategoryChooseActivity.this, throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventAddCategory(AddCategoryEvent event){
        mViewModel.addCategory(event.getMsg());
    }
}
