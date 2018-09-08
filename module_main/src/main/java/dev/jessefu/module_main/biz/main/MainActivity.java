package dev.jessefu.module_main.biz.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.jessefu.component_base.base.BaseActivity;
import dev.jessefu.component_base.router.RouterConstants;
import dev.jessefu.module_main.R;
import dev.jessefu.module_main.R2;
@Route(path = RouterConstants.ModuleMain.ACTIVITY_MAIN)
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @BindView(R2.id.tb_main)
    Toolbar mToolbar;
    @BindView(R2.id.tv_main_title)
    TextView mTvTitle;
    @BindView(R2.id.iv_main_search)
    ImageView mIvSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R2.id.iv_main_search)
    public void onClick(View view){
        //navigate 2 searchActivity
    }
}
