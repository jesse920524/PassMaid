package dev.jessefu.module_main.adapter;

import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.module_main.R;
import dev.jessefu.module_main.R2;

public class RvStarAdapter extends BaseQuickAdapter<AccountEntity, RvStarAdapter.RvStarViewHolder> {
    private static final String TAG = "RvStarAdapter";

    /**
     * recommand use this constructor*/
    public RvStarAdapter(){
        this(R.layout.main_item_rv_star);
    }

    public RvStarAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(RvStarViewHolder helper, AccountEntity item) {
        helper.mTvTitle.setText(item.getTitle());
        helper.mTvAccount.setText(item.getAccount());

        StringBuilder pwdAsterisk = new StringBuilder();
        for (int i=0; i<item.getPassword().length(); i++){
            pwdAsterisk.append("*");
        }
        helper.mTvPwd.setText(pwdAsterisk.toString());

        /**textDrawable*/
        char[] chars = item.getTitle().toCharArray();
        String firstLetter = String.valueOf(chars[0]);
        TextDrawable textDrawable = TextDrawable.builder()
                .buildRound(firstLetter, ActivityCompat.getColor(mContext, R.color.colorPrimary));
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

    protected static class RvStarViewHolder extends BaseViewHolder{
        @BindView(R2.id.tv_item_star_title)
        TextView mTvTitle;
        @BindView(R2.id.tv_item_star_account)
        TextView mTvAccount;
        @BindView(R2.id.tv_item_star_pwd)
        TextView mTvPwd;
        @BindView(R2.id.iv_item_star_avatar)
        ImageView mIvAvatar;
        @BindView(R2.id.iv_item_star_see)
        ImageView mIvSee;

        public RvStarViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
