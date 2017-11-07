package com.example.chrisantuseze.hadum.Notification.Calender;

import android.content.Context;
import android.database.Cursor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by CHRISANTUS EZE on 21/10/2017.
 */

public class DatabaseQuery extends DatabaseObject {

    private static final String TAG = Database.class.getSimpleName();
    public DatabaseQuery(Context context) {
        super(context);
    }
    public List<EventObjects> getAllFutureEvents(){
        Date dateToday = new Date();
        List<EventObjects> events = new ArrayList<>();
        String query = "select * from events";
        Cursor cursor = this.getDbConnection().rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String message = cursor.getString(cursor.getColumnIndexOrThrow("message"));
                String startDate = cursor.getString(cursor.getColumnIndexOrThrow("start_date"));
                //convert start date to date object
                Date reminderDate = convertStringToDate(startDate);
                if(reminderDate.after(dateToday) || reminderDate.equals(dateToday)){
                    events.add(new EventObjects(id, message, reminderDate));
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return events;
    }
    private Date convertStringToDate(String dateInString){
        DateFormat format = new SimpleDateFormat("d-MM-yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
