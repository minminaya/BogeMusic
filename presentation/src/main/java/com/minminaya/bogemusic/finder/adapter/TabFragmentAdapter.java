package com.minminaya.bogemusic.finder.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Niwa on 2017/5/26.
 */

public class TabFragmentAdapter extends FragmentStatePagerAdapter {


    private List<Fragment> mFragmentList;

    public TabFragmentAdapter(FragmentManager fm, List<Fragment> mFragmentList) {
        super(fm);
        this.mFragmentList = mFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "发现";
            case 1:
                return "我的";
        }
        return super.getPageTitle(position);
    }
}
