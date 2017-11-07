package com.example.chrisantuseze.hadum.Notification.Note;

import android.provider.BaseColumns;

/**
 * Created by CHRISANTUS EZE on 17/10/2017.
 */

public class NoteContract {

    public class NoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "mynotes";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_CONTENTS = "contents";


    }
}
