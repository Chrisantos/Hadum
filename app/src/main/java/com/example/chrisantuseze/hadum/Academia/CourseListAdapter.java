package com.example.chrisantuseze.hadum.Academia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chrisantuseze.hadum.R;

import java.util.List;

/**
 * Created by CHRISANTUS EZE on 07/11/2017.
 */

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseListHolder> {

    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public CourseListAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public CourseListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.course_layout, parent, false);
        return new CourseListHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseListHolder holder, int position) {

        String current = list.get(position);
        holder.textView.setText(current);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class CourseListHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public CourseListHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.textView);
        }
    }
}
