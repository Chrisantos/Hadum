package com.example.chrisantuseze.hadum.Timetable.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chrisantuseze.hadum.R;
import com.example.chrisantuseze.hadum.Timetable.DBs.FriDB;
import com.example.chrisantuseze.hadum.Timetable.Days.Contract;

import static android.provider.BaseColumns._ID;
import static com.example.chrisantuseze.hadum.Timetable.Days.Contract.DayEntry.COURSE;
import static com.example.chrisantuseze.hadum.Timetable.Days.Contract.DayEntry.END_TIME;
import static com.example.chrisantuseze.hadum.Timetable.Days.Contract.DayEntry.START_TIME;
import static com.example.chrisantuseze.hadum.Timetable.Days.Contract.DayEntry.VENUE;

public class FriAdapter extends RecyclerView.Adapter<FriAdapter.FriHolder> {
    private Cursor cursor;
    private Context context;
    private static final String TAG = MonAdapter.class.getSimpleName();
    private SQLiteDatabase mDb;

    public FriAdapter(Context context, Cursor cursor) {
        this.cursor = cursor;
        this.context = context;
        FriDB dbHelper = new FriDB(context);
        mDb = dbHelper.getWritableDatabase();
    }

    @Override
    public FriHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mon_rec_layout, parent, false);
        return new FriHolder(view);
    }

    @Override
    public void onBindViewHolder(FriHolder holder, int position) {
        if (!cursor.moveToPosition(position))return;

        int idIndex = cursor.getColumnIndex(Contract.DayEntry._ID);
        int courseIndex = cursor.getColumnIndex(COURSE);
        int venueIndex = cursor.getColumnIndex(VENUE);
        int startIndex = cursor.getColumnIndex(START_TIME);
        int endIndex = cursor.getColumnIndex(END_TIME);

        Log.d(TAG, ""+courseIndex);

        final long id = cursor.getInt(idIndex);
        String course = cursor.getString(courseIndex);
        String venue = cursor.getString(venueIndex);
        String start = cursor.getString(startIndex);
        String end = cursor.getString(endIndex);

        holder.itemView.setTag(id);
        holder.etCourse.setText(course);
        holder.etVenue.setText(venue);
        holder.tvStart.setText(start);
        holder.tvEnd.setText(end);

    }

    @Override
    public int getItemCount() {
        if (cursor==null){
            return  0;
        }
        return cursor.getCount();
    }

    public class FriHolder extends RecyclerView.ViewHolder{

        TextView etCourse, etVenue;
        TextView tvStart, tvEnd;
        private  final Context context;
        public FriHolder(final View itemView) {
            super(itemView);
            etCourse = (TextView)itemView.findViewById(R.id.course);
            etVenue = (TextView)itemView.findViewById(R.id.venue);
            tvStart = (TextView)itemView.findViewById(R.id.start);
            tvEnd = (TextView)itemView.findViewById(R.id.end);

            context = itemView.getContext();

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    long id = (long)itemView.getTag();
                    removeQuest(id);
                    swapCursor(getAllGuests());

                    Toast.makeText(context,"Deleted!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

        }
    }
    public void swapCursor(Cursor c){
        if (cursor == c){
            return;
        }
        this.cursor = c;

        if (c!=null){
            this.notifyDataSetChanged();
        }
    }
    private Cursor getAllGuests() {
        // COMPLETED (6) Inside, call query on mDb passing in the table name and projection String [] order by COLUMN_TIMESTAMP
        return mDb.query(
                FriDB.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
    private boolean removeQuest(long id){
        return mDb.delete(FriDB.TABLE_NAME, _ID+"="+id, null)>0;
    }
}
