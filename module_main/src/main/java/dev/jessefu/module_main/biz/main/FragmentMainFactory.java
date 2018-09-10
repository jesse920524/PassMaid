package dev.jessefu.module_main.biz.main;

import dev.jessefu.component_base.base.BaseFragment;
import dev.jessefu.module_main.biz.category.view.CategoryFragment;
import dev.jessefu.module_main.biz.setting.view.SettingFragment;
import dev.jessefu.module_main.biz.star.view.StarFragment;

public class FragmentMainFactory<T extends BaseFragment> {
    private static final String TAG = "FragmentMainFactory";

    public static final String STAR = StarFragment.class.getSimpleName();
    public static final String CATEGORY = CategoryFragment.class.getSimpleName();
    public static final String SETTING = SettingFragment.class.getSimpleName();

    public static <T extends BaseFragment> T  create(String name){
        T fragment = null;
        if (name.equals(STAR)){
            fragment = (T) StarFragment.newInstance("");
        }else if (name.equals(CATEGORY)){
            fragment = (T)CategoryFragment.newInstance("");
        }else if (name.equals(SETTING)){
            fragment = (T)SettingFragment.newInstance("");
        }else{
            throw new IllegalStateException("no such type fragment");
        }
        return fragment;
    }

}
