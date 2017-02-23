package com.example.manasaa.remindertask;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;



public class MainActivity extends AppCompatActivity  {
    private static final String TAG="MAINACTIVITYclass";
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private PagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"called onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //Giving Tab names
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab1_title));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab2_title));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab3_title));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        //From PagerAdapter Class assinging Fragments to tabs
         mAdapter = new PagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
