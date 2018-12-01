package dev.jessefu.module_details.details.view;

import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import dev.jessefu.component_base.base.BaseActivity;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.component_base.json.GsonFactory;
import dev.jessefu.component_base.router.Router;
import dev.jessefu.component_base.router.RouterConstants;
import dev.jessefu.module_details.R;
import dev.jessefu.module_details.R2;

@Route(path = RouterConstants.ModuleDetails.ACTIVITY_DETAILS)
public class DetailsActivity extends BaseActivity {
    private static final String TAG = "DetailsActivity";

    @Autowired(name = RouterConstants.ModuleDetails.ACTIVITY_DETAIL_BUNDLE_KEY)
    String jsonAccountEntity;
    
    private AccountEntity mEntity;
    
    @BindView(R2.id.rl_details_account)
    RelativeLayout mRlAccount;
    @BindView(R2.id.rl_details_pwd)
    RelativeLayout mRlPwd;
    @BindView(R2.id.rl_details_category)
    RelativeLayout mRlCategory;
    @BindView(R2.id.tv_details_account)
    TextView mTvAccount;
    @BindView(R2.id.tv_details_pwd)
    TextView mTvPwd;
    @BindView(R2.id.tv_details_category)
    TextView mTvCategory;
    @BindView(R2.id.iv_details_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_details_title)
    TextView mTvTitle;
    @BindView(R2.id.tv_details)
    TextView mTvToolbarTitle;
    @BindView(R2.id.fab_details)
    FloatingActionButton mFab;
    

    @Override
    protected int getLayoutRes() {
        return R.layout.details_activity_details;
    }

    @Override
    protected void initViews() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.INSTANCE.toModifyActivity(mEntity);
            }
        });
    }

    @Override
    protected void initData() {
        Log.d(TAG, "initData: "  + jsonAccountEntity);
         mEntity = GsonFactory.get().fromJson(jsonAccountEntity, AccountEntity.class);
        mTvToolbarTitle.setText(mEntity.getTitle());
        mTvAccount.setText(mEntity.getAccount());
        mTvPwd.setText(mEntity.getPassword());
        mTvCategory.setText(mEntity.getCategory());

    }

    @Override
    protected void initViewModel() {
        //no vm
    }
}
