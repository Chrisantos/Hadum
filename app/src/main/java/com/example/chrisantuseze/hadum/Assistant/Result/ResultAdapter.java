package com.example.chrisantuseze.hadum.Assistant.Result;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chrisantuseze.hadum.Assistant.Todo.TaskContract;
import com.example.chrisantuseze.hadum.R;

import static com.example.chrisantuseze.hadum.Assistant.Todo.TaskContract.TaskEntry.COLUMN_DESCRIPTION;

/**
 * Created by CHRISANTUS EZE on 29/12/2017.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {
    private Cursor cursor;
    private Context context;

    public ResultAdapter(Cursor cursor, Context context) {
        this.cursor = cursor;
        this.context = context;
    }

    @Override
    public ResultAdapter.ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_result, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultAdapter.ResultViewHolder holder, int position) {
        if (!cursor.moveToPosition(position))return;

        int idIndex = cursor.getColumnIndex(TaskContract.TaskEntry._ID);
        int descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);

        final long id = cursor.getInt(idIndex);
        String description = cursor.getString(descriptionIndex);


        holder.itemView.setTag(id);
        holder.textView.setText(description);
    }

    @Override
    public int getItemCount() {
        if (cursor==null){
            return  0;
        }
        return cursor.getCount();
    }
    public class ResultViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ResultViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.tv);

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
