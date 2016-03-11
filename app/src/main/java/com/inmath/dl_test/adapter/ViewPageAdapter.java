package com.inmath.dl_test.adapter;

import android.app.ListFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/3/11.
 */
public class ViewPageAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments=new ArrayList<>();
    List<String> items=new ArrayList<>();

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
        //从main中传递进来的fragement
    }
    public void addFragment(Fragment f,String title){
        fragments.add(f);
        items.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //得到对应position的Fragment的title
        return super.getPageTitle(position);
    }
}
