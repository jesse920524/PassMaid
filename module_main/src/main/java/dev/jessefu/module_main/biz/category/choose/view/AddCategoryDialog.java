package dev.jessefu.module_main.biz.category.choose.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.jessefu.component_base.event.AddCategoryEvent;
import dev.jessefu.module_main.R;
import dev.jessefu.module_main.R2;

public class AddCategoryDialog extends DialogFragment {
    private static final String TAG = "AddCategoryDialog";

    public static AddCategoryDialog newInstance(){
        AddCategoryDialog dialog = new AddCategoryDialog();
        return dialog;
    }

    @BindView(R2.id.et_dfac)
    EditText mEtName;
    @BindView(R2.id.btn_dfac_confirm)
    Button mBtnConfirm;
    @BindView(R2.id.btn_dfac_cancel)
    Button mBtnCancel;

    private View mRoot;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.main_dialog_fragment_add_category, container, false);
        ButterKnife.bind(this, mRoot);
        initViews();
        return mRoot;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initViews() {
        mBtnConfirm.setOnClickListener(new BtnConfirmListener());
        // TODO: 2018-12-08 btnCancel handing
    }

    protected class BtnConfirmListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            final String sInput = mEtName.getText().toString();
            if (TextUtils.isEmpty(sInput)){
                Toast.makeText(getContext(), "分类名称不能为空!", Toast.LENGTH_SHORT).show();
            }else if (sInput.length() > 10){
                Toast.makeText(getContext(), "分类名称过长, 请重新输入!", Toast.LENGTH_SHORT).show();
            }else{
                dismiss();
                EventBus.getDefault().post(AddCategoryEvent.newInstance(sInput));
            }
        }
    }
}
