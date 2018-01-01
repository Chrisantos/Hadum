package com.example.chrisantuseze.hadum.Academia;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.chrisantuseze.hadum.Assistant.Result.GPACalculator;
import com.example.chrisantuseze.hadum.R;
import com.example.chrisantuseze.hadum.Assistant.Result.ResultAdapter;
import com.example.chrisantuseze.hadum.Assistant.Result.ResultDatabaseHelper;
import com.example.chrisantuseze.hadum.Assistant.Result.ResultSharedPreference;

import static android.provider.BaseColumns._ID;
import static com.example.chrisantuseze.hadum.Assistant.Result.ResultContract.ResultEntry.TABLE_NAME;

public class Results extends AppCompatActivity {

    private ResultAdapter adapter;
    RecyclerView recyclerView;
    private SQLiteDatabase mDb;
    Toolbar mToolbar;
    private TextView mCGPA, mCGPAText, mTotalUnits, mUnitsText, mAggregate, mAggregateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        mToolbar = (Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Results");

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCGPA = (TextView)findViewById(R.id.result_cgpa);
        mCGPAText = (TextView)findViewById(R.id.tv_cgpa);
        mTotalUnits = (TextView)findViewById(R.id.value_total_units);
        mUnitsText = (TextView)findViewById(R.id.tv_total_units);
        mAggregate = (TextView)findViewById(R.id.value_total_aggregate);
        mAggregateText = (TextView)findViewById(R.id.tv_total_aggregate);

        ResultSharedPreference sharedPreference = new ResultSharedPreference(this);
        mCGPA.setText(sharedPreference.getKeyCgpa());
        mTotalUnits.setText(sharedPreference.getKeyTotalUnits());
        mAggregate.setText(sharedPreference.getKeyTotalAggregate());

        Typeface custom_font_1 = Typeface.createFromAsset(getAssets(),  "fonts/Aller_Rg.ttf");
        mCGPA.setTypeface(custom_font_1);
        mCGPAText.setTypeface(custom_font_1);
        mTotalUnits.setTypeface(custom_font_1);
        mUnitsText.setTypeface(custom_font_1);
        mAggregate.setTypeface(custom_font_1);
        mAggregateText.setTypeface(custom_font_1);


        ResultDatabaseHelper dbHelper = new ResultDatabaseHelper(this);
        mDb = dbHelper.getWritableDatabase();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        Cursor cursor = getAllGuests();
        adapter = new ResultAdapter(cursor, this);

        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Results.this, GPACalculator.class));
                finish();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long)viewHolder.itemView.getTag();

                removeQuest(id);
                adapter.swapCursor(getAllGuests());

            }
        }).attachToRecyclerView(recyclerView);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    private Cursor getAllGuests() {
        // COMPLETED (6) Inside, call query on mDb passing in the table name and projection String [] order by COLUMN_TIMESTAMP
        return mDb.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
    private boolean removeQuest(long id){
        return mDb.delete(TABLE_NAME, _ID+"="+id, null)>0;
    }

}
