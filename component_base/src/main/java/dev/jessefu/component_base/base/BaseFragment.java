package dev.jessefu.component_base.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dev.jessefu.component_base.router.Router;

public abstract class BaseFragment extends Fragment {
    /**fragment默认参数.
     * 用于fragment初始化时传入
     * 若派生类fragment需要多个参数, 由派生类自己去定义.
     * */
    public static final String FRAGMENT_ARGUMENT = "fragment_argument";

    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Router.INSTANCE.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRoot = inflater.inflate(provideLayoutRes(), container, false);
        mUnbinder = ButterKnife.bind(this, mRoot);
        initViews(mRoot);
        initViewModel();
        initData();
        return mRoot;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    protected abstract int provideLayoutRes();

    protected abstract void initViews(View view);

    protected abstract void initViewModel();
    protected abstract void initData();
}
