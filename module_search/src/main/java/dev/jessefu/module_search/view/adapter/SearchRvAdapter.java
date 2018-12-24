package dev.jessefu.module_search.view.adapter;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.module_search.R;
import dev.jessefu.module_search.R2;

public class SearchRvAdapter extends BaseQuickAdapter<AccountEntity, SearchRvAdapter.SearchVH> {
    private static final String TAG = "SearchRvAdapter";

    public SearchRvAdapter(){
        this(R.layout.search_item);
    }

    public SearchRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(SearchVH helper, AccountEntity item) {
        helper.mTvTitle.setText(item.getTitle());
        helper.mTvAccount.setText(item.getAccount());

        StringBuilder pwdAsterisk = new StringBuilder();
        for (int i=0; i<item.getPassword().length(); i++){
            pwdAsterisk.append("*");
        }
        helper.mTvPwd.setText(pwdAsterisk.toString());

        /**textDrawable*/
        char[] chars = item.getTitle().toCharArray();
        String firstLetter = String.valueOf(chars[0]).toUpperCase();

        ColorGenerator colorGenerator = ColorGenerator.MATERIAL;
        TextDrawable textDrawable = TextDrawable.builder()
                .buildRound(firstLetter, colorGenerator.getRandomColor());
        helper.mIvAvatar.setImageDrawable(textDrawable);

        helper.mIvSee.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        helper.mTvPwd.setText(item.getPassword());
                        break;
                    case MotionEvent.ACTION_UP:
                        helper.mTvPwd.setText(pwdAsterisk.toString());
                        break;
                }
                return true;
            }
        });
    }

    protected class SearchVH extends BaseViewHolder{
        @BindView(R2.id.iv_item_search_see)
        ImageView mIvSee;
        @BindView(R2.id.iv_item_search_avatar)
        ImageView mIvAvatar;
        @BindView(R2.id.tv_item_search_title)
        TextView mTvTitle;
        @BindView(R2.id.tv_item_search_account)
        TextView mTvAccount;
        @BindView(R2.id.tv_item_search_pwd)
        TextView mTvPwd;
        public SearchVH(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
