package dev.jessefu.module_main.biz.category.choose.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.util.Util;

import org.greenrobot.eventbus.EventBus;

import java.util.Optional;
import java.util.function.Function;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.jessefu.component_base.event.DeleteCategoryEvent;
import dev.jessefu.component_base.util.ScreenUtils;
import dev.jessefu.module_main.R;
import dev.jessefu.module_main.R2;

public class LongClickCategoryDialog extends DialogFragment {
    private static final String TAG = "LongClickCategoryDialog";

    public static LongClickCategoryDialog newInstance(@NonNull String name){
        LongClickCategoryDialog dialog = new LongClickCategoryDialog();
        dialog.sName = name;
        return dialog;
    }

    @BindView(R2.id.iv_dflc_icon)
    ImageView mIvIcon;
    @BindView(R2.id.tv_dflc_text)
    TextView mTvText;
    @BindView(R2.id.rl_dflc_root)
    RelativeLayout mRlRoot;

    private View mRoot;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.main_dialog_fragment_longclick_category, container, false);
        ButterKnife.bind(this, mRoot);
        initViews(mRoot);
        return mRoot;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(100 * 4, 200);
    }

    private String sName;

    private void initViews(View view) {
        mRlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(DeleteCategoryEvent.newInstance(sName));
            }
        });
    }

}
