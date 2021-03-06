package com.example.lianxizhoukao2;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * date:2018.12.27
 * author:赵颖冰(lenovo)
 * function:
 */
class MyFragmentPager extends FragmentPagerAdapter {
    List<Fragment> mFragments;
    List<String> mTitles;

    public MyFragmentPager(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        mFragments=fragments;
        mTitles=titles;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
