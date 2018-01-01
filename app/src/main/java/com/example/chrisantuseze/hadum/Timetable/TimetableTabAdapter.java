package com.example.chrisantuseze.hadum.Timetable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.chrisantuseze.hadum.Timetable.Days.FRIDAY;
import com.example.chrisantuseze.hadum.Timetable.Days.MONDAY;
import com.example.chrisantuseze.hadum.Timetable.Days.THURSDAY;
import com.example.chrisantuseze.hadum.Timetable.Days.TUESDAY;
import com.example.chrisantuseze.hadum.Timetable.Days.WEDNESDAY;

/**
 * Created by CHRISANTUS EZE on 30/10/2017.
 */

public class TimetableTabAdapter extends FragmentPagerAdapter {

    public TimetableTabAdapter(FragmentManager fm) {
        super(fm);
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
        return 5;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0: return "MONDAY";
            case 1: return "TUESDAY";
            case 2: return "WEDNESDAY";
            case 3: return "THURSDAY";
            case 4: return "FRIDAY";

            default: return null;
        }
    }
}
