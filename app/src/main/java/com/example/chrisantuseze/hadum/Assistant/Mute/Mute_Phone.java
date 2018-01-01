package com.example.chrisantuseze.hadum.Assistant.Mute;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.chrisantuseze.hadum.R;

public class Mute_Phone extends AppCompatActivity {

    private AlarmManager mute, unmute;
    private PendingIntent muteIntent, unmuteIntent;
    private Spinner spinnerHour;
    private Button btnSetMute;

    private String[] spinnerValues = {
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
    };
    private ArrayAdapter de;
    private int value;

    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mute__phone);
        mToolbar = (Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Mute Phone");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerHour = (Spinner) findViewById(R.id.spinnerhour);
        btnSetMute = (Button)findViewById(R.id.setMute);

        de = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerValues);
        de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHour.setAdapter(de);

        spinnerHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value = i + 1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnSetMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setMuteTime();
                Toast.makeText(Mute_Phone.this, "Normal ringer mode will turn on after "
                        +value+" hour(s)", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setUnMuteTime(1, 30);
                    }
                },value * 3600 * 1000);



            }
        });




    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setMuteTime(){
//        Calendar calendarMute = Calendar.getInstance();
//        calendarMute.setTimeInMillis(System.currentTimeMillis());
//
//        mute = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        Intent intentMute = new Intent(this, MyReceiver.class);
//        muteIntent = PendingIntent.getBroadcast(this, 0, intentMute, 0);
//        mute.set(AlarmManager.RTC_WAKEUP, calendarMute.getTimeInMillis(), muteIntent);
//        mute.setRepeating(AlarmManager.RTC_WAKEUP, calendarMute.getTimeInMillis(),
//                AlarmManager.INTERVAL_DAY, muteIntent);

        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }

    private void setUnMuteTime(int hour, int minute){

        Calendar calendarUnmute = Calendar.getInstance();
        calendarUnmute.set(Calendar.HOUR_OF_DAY, hour);
        calendarUnmute.set(Calendar.MINUTE, minute);

        unmute = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intentUnmute = new Intent(this, MyReceiver2.class);
        unmuteIntent = PendingIntent.getBroadcast(this, 0, intentUnmute, 0);
        unmute.set(AlarmManager.RTC_WAKEUP, calendarUnmute.getTimeInMillis(), unmuteIntent);
//        unmute.setRepeating(AlarmManager.RTC_WAKEUP, calendarUnmute.getTimeInMillis(),
//                AlarmManager.INTERVAL_DAY, unmuteIntent);
    }

}
