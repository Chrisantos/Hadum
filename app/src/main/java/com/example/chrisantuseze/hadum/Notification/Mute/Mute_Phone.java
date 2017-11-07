package com.example.chrisantuseze.hadum.Notification.Mute;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chrisantuseze.hadum.R;

import java.util.Calendar;

public class Mute_Phone extends AppCompatActivity {

    private AlarmManager mute, unmute;
    private PendingIntent muteIntent, unmuteIntent;
    private EditText btHour, btMinute;
    private Button btSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mute__phone);

        btHour = (EditText) findViewById(R.id.hour);
        btMinute = (EditText) findViewById(R.id.minute);
        btSet = (Button)findViewById(R.id.set);

        String btHourr = btHour.getText().toString();
        btHourr = btHourr.trim();

        String btMinutee = btMinute.getText().toString();
        btMinutee = btMinutee.trim();

        final int mHour = btHourr.equals(" ")? 0: Integer.parseInt(btHourr);

        final int mMinute = btMinutee.equals(" ")? 0: Integer.parseInt(btMinutee);

        btSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btHour.setText("");
                btMinute.setText("");
                setMuteTime(20,30);

                setUnMuteTime(20,30+2);
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

    private void setMuteTime(int hour, int minute){
        mute = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intentMute = new Intent(this, MyReceiver.class);
        muteIntent = PendingIntent.getBroadcast(this, 0, intentMute, 0);

        Calendar calendarMute = Calendar.getInstance();
        calendarMute.setTimeInMillis(System.currentTimeMillis());
        calendarMute.set(Calendar.HOUR_OF_DAY, hour);
        calendarMute.set(Calendar.MINUTE, minute);

        mute.setRepeating(AlarmManager.RTC_WAKEUP, calendarMute.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, muteIntent);
    }

    private void setUnMuteTime(int hour, int minute){
        unmute = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intentUnmute = new Intent(this, MyReceiver2.class);
        unmuteIntent = PendingIntent.getBroadcast(this, 0, intentUnmute, 0);

        Calendar calendarUnmute = Calendar.getInstance();
        calendarUnmute.setTimeInMillis(System.currentTimeMillis());
        calendarUnmute.set(Calendar.HOUR_OF_DAY, hour);
        calendarUnmute.set(Calendar.MINUTE, minute);

        unmute.setRepeating(AlarmManager.RTC_WAKEUP, calendarUnmute.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, muteIntent);
    }

}
