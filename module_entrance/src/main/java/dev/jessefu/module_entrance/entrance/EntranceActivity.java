package dev.jessefu.module_entrance.entrance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.jessefu.module_entrance.R;
import dev.jessefu.module_entrance.R2;

public class EntranceActivity extends AppCompatActivity{
    private static final String TAG = "EntranceActivity";

    @BindView(R2.id.entrance_tv_entrance_title)
    TextView mTvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrance_activity_entrance);
        ButterKnife.bind(this);
    }
}
