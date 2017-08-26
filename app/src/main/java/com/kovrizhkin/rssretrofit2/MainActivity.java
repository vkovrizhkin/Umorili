package com.kovrizhkin.rssretrofit2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.kovrizhkin.rssretrofit2.adapters.MainViewPagerAdapter;
import com.kovrizhkin.rssretrofit2.fragments.FavoritesFragment;
import com.kovrizhkin.rssretrofit2.fragments.FeedFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private MainViewPagerAdapter mainViewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new FeedFragment());
        mainViewPagerAdapter.addFragment(new FavoritesFragment());

        viewPager.setAdapter(mainViewPagerAdapter);

    }

}
