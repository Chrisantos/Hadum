package com.example.chrisantuseze.hadum.Timetablee.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chrisantuseze.hadum.R;
import com.example.chrisantuseze.hadum.Timetablee.Days.Contract;

import static com.example.chrisantuseze.hadum.Timetablee.Days.Contract.DayEntry.COURSE;
import static com.example.chrisantuseze.hadum.Timetablee.Days.Contract.DayEntry.END_TIME;
import static com.example.chrisantuseze.hadum.Timetablee.Days.Contract.DayEntry.START_TIME;
import static com.example.chrisantuseze.hadum.Timetablee.Days.Contract.DayEntry.VENUE;

public class MonAdapter extends RecyclerView.Adapter<MonAdapter.MonHolder> {
    private Cursor cursor;
    private Context context;
    private static final String TAG = MonAdapter.class.getSimpleName();

    public MonAdapter(Context context, Cursor cursor) {
        this.cursor = cursor;
        this.context = context;
    }

    @Override
    public MonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mon_rec_layout, parent, false);
        return new MonHolder(view);
    }

    @Override
    public void onBindViewHolder(MonHolder holder, int position) {
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

    public class MonHolder extends RecyclerView.ViewHolder{

        TextView etCourse, etVenue;
        TextView tvStart, tvEnd;

        public MonHolder(View itemView) {
            super(itemView);
            etCourse = (TextView)itemView.findViewById(R.id.course);
            etVenue = (TextView)itemView.findViewById(R.id.venue);
            tvStart = (TextView)itemView.findViewById(R.id.start);
            tvEnd = (TextView)itemView.findViewById(R.id.end);

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
}
