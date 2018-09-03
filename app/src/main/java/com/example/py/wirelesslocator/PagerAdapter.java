package com.example.py.wirelesslocator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNoOfTabs;
    public PagerAdapter(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TabFirst tab1 = new TabFirst();
                return tab1;
            case 1:
                TabSecond tab2 = new TabSecond();
                return tab2;
            case 2:
                TabThird tab3 = new TabThird();
                return tab3;
            case 3:
                TabFourth tab4 = new TabFourth();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
