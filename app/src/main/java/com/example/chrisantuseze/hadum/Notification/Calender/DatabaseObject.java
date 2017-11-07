package com.example.chrisantuseze.hadum.Notification.Calender;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by CHRISANTUS EZE on 21/10/2017.
 */

public class DatabaseObject {
    private static Database dbHelper;
    private SQLiteDatabase db;
    public DatabaseObject(Context context) {
        dbHelper = new Database(context);
        this.db = dbHelper.getWritableDatabase();
        this.db = dbHelper.getReadableDatabase();
    }
    public SQLiteDatabase getDbConnection(){
        return this.db;
    }
    public void closeDbConnection(){
        if(this.db != null){
            this.db.close();
        }
    }
}
