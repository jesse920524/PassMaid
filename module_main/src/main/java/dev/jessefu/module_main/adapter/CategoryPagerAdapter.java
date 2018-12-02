package dev.jessefu.module_main.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import dev.jessefu.component_base.db.entity.CategoryEntity;
import dev.jessefu.component_base.enums.DefaultCategory;
import dev.jessefu.module_main.biz.category.child.CategoryChildFactory;

public class CategoryPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "CategoryPagerAdapter";

    private List<Fragment> fragmentList;
    private List<String> titleList;

    public CategoryPagerAdapter(FragmentManager fm, List<CategoryEntity>List<CategoryEntity> categoryEntities) {
        super(fm);
        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        fragmentList.clear();

        // TODO: 2018-12-02 init categories
        fragmentList.add(CategoryChildFactory.create("全部"));
        titleList.add("全部");
        for (DefaultCategory defaultCategory :
                DefaultCategory.values()) {
            fragmentList.add(CategoryChildFactory.create(defaultCategory.getName()));
            titleList.add(defaultCategory.getName());
        }
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
