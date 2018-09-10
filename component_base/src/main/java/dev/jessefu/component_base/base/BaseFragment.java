package dev.jessefu.component_base.base;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
    /**fragment默认参数.
     * 用于fragment初始化时传入
     * 若派生类fragment需要多个参数, 由派生类自己去定义.
     * */
    public static final String FRAGMENT_ARGUMENT = "fragment_argument";

}
