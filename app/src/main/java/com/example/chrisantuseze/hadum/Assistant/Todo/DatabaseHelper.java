package com.example.chrisantuseze.hadum.Assistant.Todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.chrisantuseze.hadum.Assistant.Todo.TaskContract.TaskEntry.COLUMN_DESCRIPTION;
import static com.example.chrisantuseze.hadum.Assistant.Todo.TaskContract.TaskEntry.COLUMN_TIME;
import static com.example.chrisantuseze.hadum.Assistant.Todo.TaskContract.TaskEntry.TABLE_NAME;

/**
 * Created by CHRISANTUS EZE on 10/10/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE = "todo.db";
    private static final int VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "CREATE TABLE "  + TABLE_NAME + " (" +
                _ID                + " INTEGER PRIMARY KEY, " +
                COLUMN_DESCRIPTION + " TEXT , " +
                COLUMN_TIME    + " TEXT);";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
