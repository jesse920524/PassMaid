package dev.jessefu.module_search.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import dev.jessefu.component_base.base.BaseActivity;
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
    @BindView(R2.id.rv_search)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private SearchRvAdapter mAdapter;

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
        setSupportActionBar(mToolbar);

    }

    @Override
    protected void initData() {

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
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconified(false);
        searchView.setQueryHint("");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(SearchActivity.this, s, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(TAG, "onQueryTextChange: " + s);
                return true;
            }
        });

        SearchView.SearchAutoComplete mSearchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        mSearchAutoComplete.setTextColor(getResources().getColor(R.color.white));
        mSearchAutoComplete.setHintTextColor(getResources().getColor(R.color.white));
        return super.onCreateOptionsMenu(menu);

    }
}
