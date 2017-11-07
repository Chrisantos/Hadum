package com.example.chrisantuseze.hadum.Timetablee.Days;

import android.provider.BaseColumns;

/**
 * Created by CHRISANTUS EZE on 30/10/2017.
 */

public class Contract {
    private Contract(){}

    //public static final String AUTHORITY = "com.example.chrisantuseze.hadum";
    //public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+AUTHORITY);
    //public static final String PATH_TASKS = "tasks";

    public class DayEntry implements BaseColumns {
        public static final String COURSE = "course";
        public static final String VENUE = "hall";
        public static final String START_TIME = "start";
        public static final String END_TIME = "end";

    }
}
