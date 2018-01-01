package com.example.chrisantuseze.hadum.Assistant.Todo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;

import com.example.chrisantuseze.hadum.R;

import static android.provider.BaseColumns._ID;
import static com.example.chrisantuseze.hadum.Assistant.Todo.TaskContract.TaskEntry.TABLE_NAME;

public class Todo_List extends AppCompatActivity {

    private static final String TAG = Todo_List.class.getSimpleName();

    private TaskAdapter adapter;
    RecyclerView recyclerView;
    private SQLiteDatabase mDb;
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo__list);
        mToolbar = (Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Todo");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        mDb = dbHelper.getWritableDatabase();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = getAllGuests();
        adapter = new TaskAdapter(this, cursor);

        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Todo_List.this, AddActivity.class));
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
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
