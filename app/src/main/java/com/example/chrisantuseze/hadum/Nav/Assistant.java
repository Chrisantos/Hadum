package com.example.chrisantuseze.hadum.Nav;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chrisantuseze.hadum.Assistant.Result.Authenticate;
import com.example.chrisantuseze.hadum.Assistant.Calender.CustomCalendarActivity;
import com.example.chrisantuseze.hadum.Assistant.Note.Notes;
import com.example.chrisantuseze.hadum.Assistant.Todo.Todo_List;
import com.example.chrisantuseze.hadum.R;
import com.example.chrisantuseze.hadum.Timetable.Timetable;


public class Assistant extends Fragment {

    //private RecyclerView recyclerView;

    private CardView card1, card2, card3, card4, card5;
    private TextView mNotes, mTodo, mCourse, mResults, mEvents;

    private Context context;

    public Assistant() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assistant, container, false);

        setHasOptionsMenu(true);

        card1 = (CardView)view.findViewById(R.id.card_read);
        card2 = (CardView)view.findViewById(R.id.card_todo);
//        card3 = (CardView)view.findViewById(R.id.card_course);
        card4 = (CardView)view.findViewById(R.id.card_events);
        card5 = (CardView)view.findViewById(R.id.card_results);

        mNotes = (TextView) view.findViewById(R.id.txt_read);
        mTodo = (TextView)view.findViewById(R.id.txt_todo);
//        mCourse = (TextView)view.findViewById(R.id.txt_courses);
        mResults = (TextView)view.findViewById(R.id.txt_results);
        mEvents = (TextView)view.findViewById(R.id.txt_events);

        Typeface custom_font_1 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Aller_Rg.ttf");
        mNotes.setTypeface(custom_font_1);
        mTodo.setTypeface(custom_font_1);
//        mCourse.setTypeface(custom_font_1);
        mResults.setTypeface(custom_font_1);
        mEvents.setTypeface(custom_font_1);

        context = getContext();

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Notes.class));
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Todo_List.class));
            }
        });
//        card3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                context.startActivity(new Intent(context, Courses.class));
//            }
//        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CustomCalendarActivity.class));
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Authenticate.class));
            }
        });


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.timetable_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.timetable){
            startActivity(new Intent(getActivity(), Timetable.class));
            //setTitle("Timetable");
//            Timetablee timetablee = new Timetablee();
//            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.fragment_container, timetablee).commit();
        }
        return super.onOptionsItemSelected(item);
    }

}
