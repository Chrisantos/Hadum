package com.example.chrisantuseze.hadum.Timetable;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.chrisantuseze.hadum.R;

public class Timetable extends AppCompatActivity {

    private Toolbar mToolbar;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TimetableTabAdapter mTimetableTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        mToolbar = (Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("TimeTable");

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = (ViewPager)findViewById(R.id.view_pager);
        mTabLayout = (TabLayout)findViewById(R.id.mainTabs);

        mTimetableTabAdapter = new TimetableTabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mTimetableTabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
