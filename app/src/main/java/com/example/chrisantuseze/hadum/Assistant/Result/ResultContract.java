package com.example.chrisantuseze.hadum.Assistant.Result;

import android.provider.BaseColumns;

/**
 * Created by CHRISANTUS EZE on 29/12/2017.
 */

public class ResultContract {

    private ResultContract(){}

    //public static final String AUTHORITY = "com.example.chrisantuseze.hadum";
    //public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+AUTHORITY);
    public static final String PATH_TASKS = "tasks";

    public class ResultEntry implements BaseColumns {

        public static final String TABLE_NAME = "result";
        public static final String COLUMN_DESCRIPTION = "description";

    }
}
