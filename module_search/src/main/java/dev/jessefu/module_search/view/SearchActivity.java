package dev.jessefu.module_search.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import butterknife.BindView;
import dev.jessefu.component_base.base.BaseActivity;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.component_base.router.RouterConstants;
import dev.jessefu.module_search.R;
import dev.jessefu.module_search.R2;
import dev.jessefu.module_search.view.adapter.SearchRvAdapter;
import dev.jessefu.module_search.vm.SearchVM;

@Route(path = RouterConstants.ModuleSearch.ACTIVITY_SEARCH)
public class SearchActivity extends BaseActivity {
    private static final String TAG = "SearchActivity";

    @BindView(R2.id.tb_search)
    Toolbar mToolbar;
    @BindView(R2.id.iv_search_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_search_title)
    TextView mTvTitle;
    @BindView(R2.id.rv_search)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private SearchRvAdapter mAdapter;

    private SearchView mSearchView;
    private SearchView.SearchAutoComplete mSearchAutoComplete;
    private OnClickNavi onClickNavi;

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
        onClickNavi = new OnClickNavi();
        setSupportActionBar(mToolbar);
        mIvBack.setOnClickListener(onClickNavi);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new SearchRvAdapter();
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mViewModel.getLiveDataResult().observe(this, new Observer<List<AccountEntity>>() {
            @Override
            public void onChanged(@Nullable List<AccountEntity> accountEntities) {
                mAdapter.setNewData(accountEntities);
            }
        });
    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(SearchVM.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.item_action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mTvTitle.setVisibility(View.VISIBLE);
                return false;
            }
        });
        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvTitle.setVisibility(View.VISIBLE);
            }
        });
        mSearchView.setIconified(false);
        mSearchView.setQueryHint("");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
//                Toast.makeText(SearchActivity.this, s, Toast.LENGTH_SHORT).show();
                mViewModel.query(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(TAG, "onQueryTextChange: " + s);
                return true;
            }
        });

        mSearchAutoComplete = mSearchView.findViewById(R.id.search_src_text);
        mSearchAutoComplete.setTextColor(getResources().getColor(R.color.white));
        mSearchAutoComplete.setHintTextColor(getResources().getColor(R.color.white));
        return super.onCreateOptionsMenu(menu);
    }


    protected class OnClickNavi implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if (mSearchAutoComplete.isShown()){
                mSearchAutoComplete.setText("");//clear text

                //use reflect to invoke searchView#onCloseClicked method
                Method method = null;
                try {
                    method = mSearchView.getClass().getDeclaredMethod("onCloseClicked");
                    method.setAccessible(true);
                    method.invoke(mSearchView);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }else{
                finish();
            }
        }
    }
}
