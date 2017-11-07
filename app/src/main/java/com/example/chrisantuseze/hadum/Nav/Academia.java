package com.example.chrisantuseze.hadum.Nav;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chrisantuseze.hadum.Academia.Books;
import com.example.chrisantuseze.hadum.Academia.Courses;
import com.example.chrisantuseze.hadum.Academia.Results;
import com.example.chrisantuseze.hadum.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Academia extends Fragment {
    //private RecyclerView recyclerView;
    private CardView card1, card2, card3;

    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_academia, container, false);

//        recyclerView = (RecyclerView)view.findViewById(R.id.acad_recycler_view);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
//        recyclerView.setLayoutManager(gridLayoutManager);
//
//        AcademiaAdapter adapter = new AcademiaAdapter();
//        recyclerView.setAdapter(adapter);

        card1 = (CardView)view.findViewById(R.id.card_course);

        card2 = (CardView)view.findViewById(R.id.card_results);
        ;
        card3 = (CardView)view.findViewById(R.id.card_books);


        context = getContext();

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Courses.class));
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Results.class));
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Books.class));
            }
        });
//        card4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                context.startActivity(new Intent(context, PastQnA.class));
//            }
//        });


        return view;
    }

}
