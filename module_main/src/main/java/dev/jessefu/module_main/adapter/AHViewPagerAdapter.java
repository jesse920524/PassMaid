package dev.jessefu.module_main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import dev.jessefu.module_main.biz.category.view.CategoryFragment;
import dev.jessefu.module_main.biz.main.FragmentMainFactory;
import dev.jessefu.module_main.biz.star.view.StarFragment;

public class AHViewPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "AHViewPagerAdapter";

    private List<Fragment> fragmentList;
    private Fragment mCurrentFragment;

    public AHViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();

        fragmentList.clear();
        fragmentList.add(FragmentMainFactory.create(FragmentMainFactory.STAR));
        fragmentList.add(FragmentMainFactory.create(FragmentMainFactory.CATEGORY));
        fragmentList.add(FragmentMainFactory.create(FragmentMainFactory.SETTING));

        mCurrentFragment = fragmentList.get(0);
    }

    @Override
    public Fragment getItem(int i) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
