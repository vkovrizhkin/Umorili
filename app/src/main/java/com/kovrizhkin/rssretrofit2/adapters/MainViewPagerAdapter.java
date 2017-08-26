package com.kovrizhkin.rssretrofit2.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kovrizhkin V.A. on 26.08.2017.
 */


public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private static List<Fragment> fragmentList;

    private List<String> titles = Arrays.asList("ЛЕНТА", "ИЗБРАННОЕ");

    private final static int tabCount = 2;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
    }

    public void addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragmentList.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
