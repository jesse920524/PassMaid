package dev.jessefu.component_base.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dev.jessefu.component_base.router.Router;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Router.INSTANCE.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
