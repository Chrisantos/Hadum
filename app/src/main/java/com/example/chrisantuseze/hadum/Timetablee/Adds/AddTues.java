package com.example.chrisantuseze.hadum.Timetablee.Adds;

import android.annotation.TargetApi;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.chrisantuseze.hadum.R;
import com.example.chrisantuseze.hadum.Timetablee.DBs.TuesDB;

import static com.example.chrisantuseze.hadum.Timetablee.DBs.TuesDB.TABLE_NAME;
import static com.example.chrisantuseze.hadum.Timetablee.Days.Contract.DayEntry.*;

public class AddTues extends AppCompatActivity {
    private EditText etCourse, etVenue;
    private TextView tvStart, tvEnd;

    private SQLiteDatabase db;
    private TuesDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tues);

        etCourse = (EditText)findViewById(R.id.course);
        etVenue = (EditText)findViewById(R.id.venue);
        tvStart = (TextView) findViewById(R.id.select_from);
        tvEnd = (TextView) findViewById(R.id.select_to);

        FloatingActionButton actionButton = (FloatingActionButton)findViewById(R.id.fab);

        dbHelper = new TuesDB(this);
        db = dbHelper.getWritableDatabase();

        tvStart.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddTues.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tvStart.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }

        });

        tvEnd.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddTues.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tvEnd.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }

        });


        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredCourse = etCourse.getText().toString();
                String enteredVenue = etVenue.getText().toString();
                String enteredStart = tvStart.getText().toString();
                String enteredEnd = tvEnd.getText().toString();


                if(TextUtils.isEmpty(enteredCourse)){
                    Toast.makeText(AddTues.this, "You must enter a course first", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(enteredVenue)){
                    Toast.makeText(AddTues.this, "You must enter a venue first", Toast.LENGTH_LONG).show();
                    return;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put(COURSE, enteredCourse);
                contentValues.put(VENUE, enteredVenue);
                contentValues.put(START_TIME, enteredStart);
                contentValues.put(END_TIME, enteredEnd);
                db.insert(TABLE_NAME, null, contentValues);

                finish();
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
}
