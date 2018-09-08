package dev.jessefu.module_entrance.entrance.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.jessefu.component_base.base.BaseActivity;
import dev.jessefu.component_base.router.RouterConstants;
import dev.jessefu.module_entrance.R;
import dev.jessefu.module_entrance.R2;
@Route(path = RouterConstants.ModuleEntrance.ACTIVITY_ENTRANCE)
public class EntranceActivity extends BaseActivity{
    private static final String TAG = "EntranceActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrance_activity_entrance);
        ButterKnife.bind(this);
    }
}
