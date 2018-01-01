package com.example.chrisantuseze.hadum.Nav;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chrisantuseze.hadum.R;
import com.example.chrisantuseze.hadum.Settings.EditDept;
import com.example.chrisantuseze.hadum.Settings.EditLevel;
import com.example.chrisantuseze.hadum.Settings.EditName;
import com.example.chrisantuseze.hadum.Settings.EditRegNo;


/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment {
    private TextView mEditName, mEditRegNo, mEditDept, mEditLevel;
    private LinearLayout mLayoutName, mLayoutRegNo, mLayoutDept, mLayoutLevel;
    private Context context;


    public Settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        mEditDept = (TextView)view.findViewById(R.id.edit_dept);
        mEditLevel = (TextView)view.findViewById(R.id.edit_level);
        mEditName = (TextView)view.findViewById(R.id.edit_name);
        mEditRegNo = (TextView)view.findViewById(R.id.edit_regno);

        Typeface custom_font_1 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Aller_Rg.ttf");
        mEditName.setTypeface(custom_font_1);
        mEditRegNo.setTypeface(custom_font_1);
        mEditLevel.setTypeface(custom_font_1);
        mEditDept.setTypeface(custom_font_1);

        mLayoutDept = (LinearLayout)view.findViewById(R.id.linear_dept);
        mLayoutLevel = (LinearLayout)view.findViewById(R.id.linear_level);
        mLayoutName = (LinearLayout)view.findViewById(R.id.linear_name);
        mLayoutRegNo = (LinearLayout)view.findViewById(R.id.linear_regno);

        context = getContext();

        mLayoutName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, EditName.class));
            }
        });
        mLayoutRegNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, EditRegNo.class));
            }
        });
        mLayoutLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, EditLevel.class));
            }
        });
        mLayoutDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, EditDept.class));
            }
        });

        return view;
    }

}
