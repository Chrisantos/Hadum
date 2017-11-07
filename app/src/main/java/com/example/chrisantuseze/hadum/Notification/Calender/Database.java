package com.example.chrisantuseze.hadum.Notification.Calender;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by CHRISANTUS EZE on 21/10/2017.
 */

public class Database extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "sqlite.db";
    private static final int DATABASE_VERSION = 3;
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
