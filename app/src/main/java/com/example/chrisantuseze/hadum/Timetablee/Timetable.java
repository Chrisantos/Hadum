package com.example.chrisantuseze.hadum.Timetablee;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chrisantuseze.hadum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Timetable extends Fragment {


    public Timetable() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);

        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("MONDAY"));
        tabLayout.addTab(tabLayout.newTab().setText("TUESDAY"));
        tabLayout.addTab(tabLayout.newTab().setText("WEDNESSDAY"));
        tabLayout.addTab(tabLayout.newTab().setText("THURSDAY"));
        tabLayout.addTab(tabLayout.newTab().setText("FRIDAY"));

        final ViewPager viewPager = (ViewPager)view.findViewById(R.id.pager);
        final PagerAdapter pagerAdapter = new Timetable_Tab(getFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener (new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

}
