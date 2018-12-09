package dev.jessefu.module_main.biz.category.choose.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import dev.jessefu.module_main.R;

public class LongClickCategoryDialog extends DialogFragment {
    private static final String TAG = "LongClickCategoryDialog";

    public static LongClickCategoryDialog newInstance(@NonNull String name){
        LongClickCategoryDialog dialog = new LongClickCategoryDialog();
        dialog.sName = name;
        return dialog;
    }

    private View mRoot;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.main_dialog_fragment_longclick_category, container, false);
        ButterKnife.bind(this, mRoot);
        initViews(mRoot);
        return mRoot;
    }

    private String sName;

    private void initViews(View view) {

    }

}
