package com.inmath.dl_test.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.inmath.dl_test.fragment.Fragment1;
import com.inmath.dl_test.fragment.Fragment2;
import com.inmath.dl_test.fragment.Fragment3;

import java.util.List;

/**
 * Created by asus on 2016/3/11.
 */
public class ViewPageAdapter extends FragmentPagerAdapter {

    List<String> mtitle;

    public ViewPageAdapter(FragmentManager fm,List<String> title) {
        super(fm);
        mtitle=title;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new Fragment1();

            case 1:
                return new Fragment2();

            case 2:
                return new Fragment3();

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return mtitle.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mtitle.get(position);
    }
}