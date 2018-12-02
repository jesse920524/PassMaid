package dev.jessefu.module_main.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dev.jessefu.module_main.biz.main.FragmentMainFactory;

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
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (getCurrentFragment() != object){
            mCurrentFragment = (Fragment) object;
        }
        super.setPrimaryItem(container, position, object);
    }

    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }
}
