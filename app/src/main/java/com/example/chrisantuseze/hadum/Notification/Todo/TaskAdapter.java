package com.example.chrisantuseze.hadum.Notification.Todo;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chrisantuseze.hadum.R;

import static com.example.chrisantuseze.hadum.Notification.Todo.TaskContract.TaskEntry.COLUMN_DESCRIPTION;
import static com.example.chrisantuseze.hadum.Notification.Todo.TaskContract.TaskEntry.COLUMN_TIME;
//import static com.example.chrisantuseze.hadum.Notification.Todo.TaskContract.TodoEntry.DESCRIPTION;
//import static com.example.chrisantuseze.hadum.Notification.Todo.TaskContract.TodoEntry.PRIORITY;
//import static com.example.chrisantuseze.hadum.Notification.Todo.TaskContract.TodoEntry.TIME;

/**
 * Created by CHRISANTUS EZE on 10/10/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {
    private Cursor cursor;
    private Context context;
    private static final String TAG = TaskAdapter.class.getSimpleName();

    public TaskAdapter(Context context, Cursor cursor) {
        this.cursor = cursor;
        this.context = context;
    }

    @Override
    public TaskAdapter.TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.m_layout, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskAdapter.TaskHolder holder, int position) {
        if (!cursor.moveToPosition(position))return;

        int idIndex = cursor.getColumnIndex(TaskContract.TaskEntry._ID);
        int descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);
        int timeIndex = cursor.getColumnIndex(COLUMN_TIME);

        final long id = cursor.getInt(idIndex);
        String description = cursor.getString(descriptionIndex);
        String time = cursor.getString(timeIndex);
        String letter = description.substring(0,1);
        String lettr = letter.toUpperCase();

        holder.itemView.setTag(id);
        holder.taskDescripView.setText(description);
        holder.timeDescription.setText(time);
        holder.taskLetter.setText(lettr);
//
//        GradientDrawable letterCircle = (GradientDrawable)holder.taskLetter.getBackground();
//        int letterColor = getLetterColor(letter);
//        letterCircle.setColor(letterColor);

    }
    private int getLetterColor(String letter){
        int letterColor = 0;
        switch (letter){
            case "A": letterColor = ContextCompat.getColor(context, R.color.materialRed);
                break;
            case "B": letterColor = ContextCompat.getColor(context, R.color.materialOrange);
                break;
            case "C": letterColor = ContextCompat.getColor(context, R.color.materialYellow);
                break;
            case "D": letterColor = ContextCompat.getColor(context, R.color.materialRed);
                break;
            case "E": letterColor = ContextCompat.getColor(context, R.color.materialOrange);
                break;
            case "F": letterColor = ContextCompat.getColor(context, R.color.materialYellow);
                break;
            case "G": letterColor = ContextCompat.getColor(context, R.color.materialRed);
                break;
            case "H": letterColor = ContextCompat.getColor(context, R.color.materialOrange);
                break;
            case "I": letterColor = ContextCompat.getColor(context, R.color.materialYellow);
                break;
            case "J": letterColor = ContextCompat.getColor(context, R.color.materialRed);
                break;
            case "K": letterColor = ContextCompat.getColor(context, R.color.materialOrange);
                break;
            case "L": letterColor = ContextCompat.getColor(context, R.color.materialYellow);
                break;
            case "M": letterColor = ContextCompat.getColor(context, R.color.materialRed);
                break;
            case "N": letterColor = ContextCompat.getColor(context, R.color.materialOrange);
                break;
            case "O": letterColor = ContextCompat.getColor(context, R.color.materialYellow);
                break;
            default:break;
        }
        return letterColor;
    }

    @Override
    public int getItemCount() {
        if (cursor==null){
            return  0;
        }
        return cursor.getCount();
    }

    public class TaskHolder extends RecyclerView.ViewHolder{

        TextView taskDescripView;
        TextView timeDescription;
        TextView taskLetter;

        public TaskHolder(View itemView) {
            super(itemView);
            taskDescripView = (TextView)itemView.findViewById(R.id.taskDescription);
            timeDescription = (TextView)itemView.findViewById(R.id.timeDescription);
            taskLetter = (TextView)itemView.findViewById(R.id.task_first_letter);

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
