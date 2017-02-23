package com.example.manasaa.remindertask;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by manasaa on 20-02-2017.
 * Adapter for TabLayout
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    //Constructor
    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ManasaFragment tab1 = new ManasaFragment();
                return tab1;
            case 1:
                VinayFragment tab2 = new VinayFragment();
                return tab2;
            case 2:
                SailiFragment tab3 = new SailiFragment();
                return tab3;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
