package com.example.chrisantuseze.hadum.Assistant.Result;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;

/**
 * Created by CHRISANTUS EZE on 29/12/2017.
 */

public class ResultDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE = "result.db";
    private static final int VERSION = 1;
    public ResultDatabaseHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY = "CREATE TABLE "+ ResultContract.ResultEntry.TABLE_NAME +"( "+ _ID +" INTEGER PRIMARY KEY, " +
                ResultContract.ResultEntry.COLUMN_DESCRIPTION+ " TEXT NOT NULL );";
        db.execSQL(QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ ResultContract.ResultEntry.TABLE_NAME);
        onCreate(db);
    }
}
