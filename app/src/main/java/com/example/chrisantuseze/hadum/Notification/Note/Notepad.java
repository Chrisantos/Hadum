package com.example.chrisantuseze.hadum.Notification.Note;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chrisantuseze.hadum.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@SuppressWarnings("deprecation")
public class Notepad extends AppCompatActivity {

    public static int numTitle = 1;
    public static String curDate = "";
    public static String curText = "";
    private EditText mTitleText;
    private EditText mBodyText;
    private TextView mDateText;
    private Long mRowId;

    private Cursor note;

    private NoteDB mDbHelper;

    public static class LineEditText extends android.support.v7.widget.AppCompatEditText{
        //we need this constructor for layout inflater

        public LineEditText(Context context, AttributeSet attrs){
            super(context, attrs);
            mRect = new Rect();
            mpaint = new Paint();
            mpaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mpaint.setColor(Color.rgb(0,184,212));

        }

        private Rect mRect;
        private Paint mpaint;

        @Override
        protected void onDraw(Canvas canvas){
            int height = getHeight();
            int line_height = getLineHeight();

            int count = height / line_height;

            if (getLineCount() > count)
                count = getLineCount();

            Rect r = mRect;
            Paint paint = mpaint;
            int baseline = getLineBounds(0, r);

            for (int i = 0; i <count; i++){
                canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
                baseline += getLineHeight();

                super.onDraw(canvas);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Notepad");

        mDbHelper = new NoteDB(this);
        mDbHelper.open();

        mTitleText = (EditText) findViewById(R.id.title);
        mBodyText = (EditText) findViewById(R.id.body);
        mDateText = (TextView) findViewById(R.id.notelist_date);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        curDate = df.format(c.getTime());

        mDateText.setText(""+curDate);

        mRowId = (savedInstanceState == null) ? null:
                (long) savedInstanceState.getSerializable(NoteDB.KEY_ROWID);
        if (mRowId == null){
            Bundle extras = getIntent().getExtras();
            mRowId = extras != null ? extras.getLong(NoteDB.KEY_ROWID) : null;
        }

        populateFields();

    }

    // @Override
    protected void onSavedInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        saveState();
        outState.putSerializable(NoteDB.KEY_ROWID, mRowId);

    }


    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateFields();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                if(note != null){
                    note.close();
                    note = null;
                }
                if(mRowId != null){
                    mDbHelper.deleteNote(mRowId);
                }
                finish();

                return true;
            case R.id.save:
                saveState();
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void saveState() {
        String title = mTitleText.getText().toString();
        String body = mBodyText.getText().toString();

        if (title.length() > 25){
            Toast.makeText(this, "Title too long!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(mRowId == null){
            long id = mDbHelper.createNote(title, body, curDate);
            if(id > 0){ mRowId = id; }
        }else{
            mDbHelper.updateNote(mRowId, title, body, curDate);
        }
    }

    private void populateFields() {
        if (mRowId != null) {
            note = mDbHelper.fetchNote(mRowId);
            startManagingCursor(note);
            mTitleText.setText(note.getString(
                    note.getColumnIndexOrThrow(NoteDB.KEY_TITLE)));
            mBodyText.setText(note.getString(
                    note.getColumnIndexOrThrow(NoteDB.KEY_BODY)));
            curText = note.getString(
                    note.getColumnIndexOrThrow(NoteDB.KEY_BODY));
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Notes.class));
        finish();
    }
}
