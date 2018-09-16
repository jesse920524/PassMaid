package dev.jessefu.module_main.biz.category;

import dev.jessefu.component_base.base.BaseFragment;
import dev.jessefu.module_main.biz.category.view.CategoryChildFragment;

public class FragmentCategoryChildFactory {
    private static final String TAG = "FragmentCategoryChildFa";

    public static <T extends BaseFragment> T create(String category){
        return (T) CategoryChildFragment.newInstance(category);
    }
}
