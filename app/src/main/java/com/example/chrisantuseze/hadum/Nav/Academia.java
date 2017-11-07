package com.example.chrisantuseze.hadum.Nav;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chrisantuseze.hadum.Academia.Books;
import com.example.chrisantuseze.hadum.Academia.Courses;
import com.example.chrisantuseze.hadum.Academia.Results;
import com.example.chrisantuseze.hadum.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Academia extends Fragment {
    //private RecyclerView recyclerView;
    private TextView txt1, txt2, txt3,txt4;
    private ImageView img1, img2, img3,img4;
    private CardView card1, card2, card3,card4;

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


        txt1 = (TextView)view.findViewById(R.id.txt_course);
        img1 = (ImageView) view.findViewById(R.id.img_course);
        card1 = (CardView)view.findViewById(R.id.card_course);

        txt2 = (TextView)view.findViewById(R.id.txt_results);
        img2 = (ImageView) view.findViewById(R.id.img_results);
        card2 = (CardView)view.findViewById(R.id.card_results);

        txt3 = (TextView)view.findViewById(R.id.txt_books);
        img3 = (ImageView) view.findViewById(R.id.img_books);
        card3 = (CardView)view.findViewById(R.id.card_books);

//        txt4 = (TextView)view.findViewById(R.id.txt_q_n_a);
//        img4 = (ImageView) view.findViewById(R.id.img_q_n_a);
//        card4 = (CardView)view.findViewById(R.id.card_q_n_a);

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
