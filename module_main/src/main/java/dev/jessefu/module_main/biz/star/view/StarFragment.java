package dev.jessefu.module_main.biz.star.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.jessefu.component_base.base.BaseFragment;
import dev.jessefu.module_main.R;

public class StarFragment extends BaseFragment {
    private static final String TAG = "StarFragment";

    public static StarFragment newInstance(String arg){
        Bundle bundle = new Bundle();
        bundle.putString(BaseFragment.FRAGMENT_ARGUMENT, arg);
        StarFragment instance = new StarFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment_star, container, false);

    }
}
