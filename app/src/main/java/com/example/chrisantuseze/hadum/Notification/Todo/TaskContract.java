package com.example.chrisantuseze.hadum.Notification.Todo;

import android.provider.BaseColumns;

/**
 * Created by CHRISANTUS EZE on 10/10/2017.
 */

public class TaskContract {

    private TaskContract(){}

    //public static final String AUTHORITY = "com.example.chrisantuseze.hadum";
    //public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+AUTHORITY);
    public static final String PATH_TASKS = "tasks";

    public class TaskEntry implements BaseColumns{

        public static final String TABLE_NAME = "tasks";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_LETTER = "letter";

    }
}
