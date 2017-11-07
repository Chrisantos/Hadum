package com.example.chrisantuseze.hadum.Notification.Todo;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chrisantuseze.hadum.R;

import static com.example.chrisantuseze.hadum.Notification.Todo.TaskContract.TaskEntry.COLUMN_DESCRIPTION;
import static com.example.chrisantuseze.hadum.Notification.Todo.TaskContract.TaskEntry.COLUMN_TIME;
import static com.example.chrisantuseze.hadum.Notification.Todo.TaskContract.TaskEntry.TABLE_NAME;

public class AddActivity extends AppCompatActivity {

    private EditText date, task;
    private Button btAdd;
    private DatePickerDialog datePickerDialog;

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        date = (EditText)findViewById(R.id.time_edit);

        task = (EditText)findViewById(R.id.editTextTaskDescription);
        btAdd = (Button) findViewById(R.id.add_button);

        date.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(AddActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });



        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredTask = task.getText().toString();
                String enteredDate = date.getText().toString();


                if(TextUtils.isEmpty(enteredTask)){
                    Toast.makeText(AddActivity.this, "You must enter a task first", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(enteredDate)){
                    Toast.makeText(AddActivity.this, "You must enter a date first", Toast.LENGTH_LONG).show();
                    return;
                }
                if(enteredTask.length() < 6){
                    Toast.makeText(AddActivity.this, "Task count must be more than 6", Toast.LENGTH_LONG).show();
                    return;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_DESCRIPTION, enteredTask);
                contentValues.put(COLUMN_TIME, enteredDate);
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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Todo_List.class));
        finish();
    }
}
