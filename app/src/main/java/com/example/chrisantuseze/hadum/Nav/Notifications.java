package com.example.chrisantuseze.hadum.Nav;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chrisantuseze.hadum.Notification.Calender.CustomCalendarActivity;
import com.example.chrisantuseze.hadum.Notification.Mute.Mute_Phone;
import com.example.chrisantuseze.hadum.Notification.Note.Notes;
import com.example.chrisantuseze.hadum.Notification.Todo.Todo_List;
import com.example.chrisantuseze.hadum.R;



public class Notifications extends Fragment {

    //private RecyclerView recyclerView;

    private CardView card1, card2, card3, card4;

    private Context context;

    public Notifications() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        card1 = (CardView)view.findViewById(R.id.card_read);
        card2 = (CardView)view.findViewById(R.id.card_todo);
        card3 = (CardView)view.findViewById(R.id.card_mute);
        card4 = (CardView)view.findViewById(R.id.card_events);

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
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Mute_Phone.class));
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CustomCalendarActivity.class));
            }
        });


        return view;
    }

}
