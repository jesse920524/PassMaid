package dev.jessefu.module_main.biz.category.child;

import dev.jessefu.component_base.base.BaseFragment;
import dev.jessefu.module_main.biz.category.child.view.CategoryChildFragment;

public class CategoryChildFactory {
    private static final String TAG = "FragmentCategoryChildFa";

    public static <T extends BaseFragment> T create(String category){
        return (T) CategoryChildFragment.newInstance(category);
    }
}
