package com.example.lianxizhoukao3.adapter;

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
public class MyFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragments;
    List<String> mStrings;
    public MyFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titlelist) {
        super(fm);
        mFragments=fragmentList;
        mStrings=titlelist;
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
        return mStrings.get(position);
    }
}
