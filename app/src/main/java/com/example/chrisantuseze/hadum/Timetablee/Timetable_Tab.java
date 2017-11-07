package com.example.chrisantuseze.hadum.Timetablee;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.chrisantuseze.hadum.Timetablee.Days.FRIDAY;
import com.example.chrisantuseze.hadum.Timetablee.Days.MONDAY;
import com.example.chrisantuseze.hadum.Timetablee.Days.THURSDAY;
import com.example.chrisantuseze.hadum.Timetablee.Days.TUESDAY;
import com.example.chrisantuseze.hadum.Timetablee.Days.WEDNESDAY;

/**
 * Created by CHRISANTUS EZE on 30/10/2017.
 */

public class Timetable_Tab extends FragmentPagerAdapter {
    private int numberOfTabs;

    public Timetable_Tab(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new MONDAY();
            case 1: return new TUESDAY();
            case 2: return new WEDNESDAY();
            case 3: return new THURSDAY();
            case 4: return new FRIDAY();
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
