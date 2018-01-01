package com.example.chrisantuseze.hadum.Assistant.Calender;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.chrisantuseze.hadum.R;

public class CustomCalendarActivity extends AppCompatActivity {
    private static final String TAG = CustomCalendarActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_calendar);

            CalendarCustomView mView = (CalendarCustomView)findViewById(R.id.custom_calendar);
        }
    }

