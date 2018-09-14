package dev.jessefu.module_modify.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import dev.jessefu.component_base.enums.Category;
import dev.jessefu.module_modify.R;
import dev.jessefu.module_modify.R2;

public class CategoryChooseFragment extends DialogFragment {
    private static final String TAG = "CategoryChooseFragment";

    /**called when user select one category
     * */
    public interface OnCategoryCheckedListener{
        /**@param dialogFragment dialogFragment
         * @param categoryName name */
        void onCategoryChecked(DialogFragment dialogFragment, String categoryName);
    }

    public static CategoryChooseFragment newInstance(OnCategoryCheckedListener listener){
        CategoryChooseFragment instance = new CategoryChooseFragment();
        instance.setListener(listener);
        return instance;
    }

    @BindView(R2.id.rg_modify)
    RadioGroup mRadioGroup;
    @BindView(R2.id.rb_modify_social)
    RadioButton mRbSocial;
    @BindView(R2.id.rb_modify_life)
    RadioButton mRbLife;
    @BindView(R2.id.rb_modify_shopping)
    RadioButton mRbShopping;
    @BindView(R2.id.rb_modify_game)
    RadioButton mRbGame;
    @BindView(R2.id.rb_modify_work)
    RadioButton mRbWork;
    @BindView(R2.id.rb_modify_other)
    RadioButton mRbOther;

    private View mRoot;

    private OnCategoryCheckedListener listener;
    public void setListener(OnCategoryCheckedListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.modify_dialog_fragment_category, container, false);
        ButterKnife.bind(this, mRoot);
        initViews();
        return mRoot;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (listener != null) listener = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    private void initViews() {
        mRadioGroup.check(R.id.rb_modify_social);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String categoryName = null;
                if (checkedId == R.id.rb_modify_social){
                    categoryName = Category.SOCIAL.getName();
                }else if (checkedId == R.id.rb_modify_life){
                    categoryName = Category.LIFE.getName();
                }else if (checkedId == R.id.rb_modify_shopping){
                    categoryName = Category.SHOPPING.getName();
                }else if (checkedId == R.id.rb_modify_game){
                    categoryName = Category.GAME.getName();
                }else if (checkedId == R.id.rb_modify_work){
                    categoryName = Category.WORK.getName();
                }else if (checkedId == R.id.rb_modify_other){
                    categoryName = Category.OTHER.getName();
                }else{
                    throw new IllegalArgumentException("no such fucking category");
                }
                listener.onCategoryChecked(CategoryChooseFragment.this, categoryName);
            }
        });
    }

}
