package dev.jessefu.module_main.biz.setting.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.jessefu.component_base.base.BaseFragment;
import dev.jessefu.module_main.R;

public class SettingFragment extends BaseFragment {
    private static final String TAG = "SettingFragment";

    public static SettingFragment newInstance(String arg){
        Bundle bundle = new Bundle();
        bundle.putString(BaseFragment.FRAGMENT_ARGUMENT, arg);
        SettingFragment instance = new SettingFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment_setting, container, false);
    }
}
