package dev.jessefu.module_modify.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.OnClick;
import dev.jessefu.component_base.base.BaseActivity;
import dev.jessefu.component_base.router.RouterConstants;
import dev.jessefu.module_modify.R;
import dev.jessefu.module_modify.R2;
import dev.jessefu.module_modify.vm.ModifyVM;

@Route(path = RouterConstants.ModuleModify.ACTIVITY_MODIFY)
public class ModifyActivity extends BaseActivity {
    private static final String TAG = "ModifyActivity";

    @Autowired(name = RouterConstants.ModuleModify.ACTIVITY_MODIFY_JSON)
    String jsonAccountEntity;//AccountEntity的json格式

    @BindView(R2.id.iv_modify_cancel)
    ImageView mIvCancel;
    @BindView(R2.id.iv_modify_confirm)
    ImageView mIvConfirm;
    @BindView(R2.id.et_modify_title)
    MaterialEditText mEtTitle;
    @BindView(R2.id.et_modify_account)
    MaterialEditText mEtAccount;
    @BindView(R2.id.et_modify_pwd)
    MaterialEditText mEtPwd;
    @BindView(R2.id.et_modify_description)
    MaterialEditText mEtDescription;
    @BindView(R2.id.cb_modify)
    CheckBox mCheckBox;
    @BindView(R2.id.ll_modify_category)
    LinearLayout mLayoutCategory;
    @BindView(R2.id.tv_modify_category)
    TextView mTvCategory;

    private ModifyVM viewModel;

    @Override
    protected int getLayoutRes() {
        return R.layout.modify_activity_modify;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        viewModel.getLiveDataFinish()
                .observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        finish();
                    }
                });
    }

    @Override
    protected void initViewModel() {
        ViewModelProviders.of(this)
                .get(ModifyVM.class);
    }

    @OnClick(R2.id.ll_modify_category)
    public void onClickCategory(View view){
        Log.d(TAG, "onClick: exec");
    }

    @OnClick(R2.id.iv_modify_cancel)
    public void onClickCancel(View view){
        finish();
    }

    @OnClick(R2.id.iv_modify_confirm)
    public void onClickConfirm(View view){
        viewModel.submitData(mEtTitle.getText().toString(),
                mEtAccount.getText().toString(),
                mEtPwd.getText().toString(),
                mEtDescription.getText().toString(),
                mCheckBox.isChecked(),
                mTvCategory.getText().toString()
                );
    }
}
