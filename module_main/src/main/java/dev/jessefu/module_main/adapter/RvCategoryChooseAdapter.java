package dev.jessefu.module_main.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.jessefu.component_base.db.entity.CategoryEntity;
import dev.jessefu.component_base.util.TextDrawableUtil;
import dev.jessefu.module_main.R;
import dev.jessefu.module_main.R2;

public class RvCategoryChooseAdapter extends BaseQuickAdapter<CategoryEntity, RvCategoryChooseAdapter.RvCategoryChooseVH> {

    public RvCategoryChooseAdapter(){
        this(R.layout.main_item_rv_category_choose);
    }

    public RvCategoryChooseAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(RvCategoryChooseVH helper, CategoryEntity item) {
        helper.mTvName.setText(item.getName());

//        /**textDrawable*/
//        char[] chars = item.getName().toCharArray();
//        String firstLetter = String.valueOf(chars[0]).toUpperCase();
//
//        ColorGenerator colorGenerator = ColorGenerator.MATERIAL;
//        TextDrawable textDrawable = TextDrawable.builder()
//                .buildRound(firstLetter, colorGenerator.getRandomColor());

        helper.mIvAvatar.setImageDrawable(TextDrawableUtil.generate(item.getName()));
    }


    protected static class RvCategoryChooseVH extends BaseViewHolder{

        @BindView(R2.id.iv_icc_avatar)
        ImageView mIvAvatar;
        @BindView(R2.id.tv_icc_name)
        TextView mTvName;

        public RvCategoryChooseVH(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
