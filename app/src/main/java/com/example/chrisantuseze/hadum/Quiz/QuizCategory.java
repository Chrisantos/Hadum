package com.example.chrisantuseze.hadum.Quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.chrisantuseze.hadum.Quiz.Categories.Geography;
import com.example.chrisantuseze.hadum.Quiz.Categories.Politics;
import com.example.chrisantuseze.hadum.Quiz.Categories.Religion;
import com.example.chrisantuseze.hadum.Quiz.Categories.Sports;
import com.example.chrisantuseze.hadum.Quiz.Categories.Technology;
import com.example.chrisantuseze.hadum.R;

public class QuizCategory extends AppCompatActivity {
    private CardView cardSports, cardPolitics, cardGeography, cardReligion, cardTech;
    private TextView tvSports, tvPolitics, tvGeography, tvReligion, tvTech;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_category);
        mToolbar = (Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Category");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        alertBox();

        cardSports = (CardView)findViewById(R.id.card_sports);
        cardPolitics = (CardView)findViewById(R.id.card_politics);
        cardGeography = (CardView)findViewById(R.id.card_geography);
        cardReligion = (CardView)findViewById(R.id.card_religion);
        cardTech = (CardView)findViewById(R.id.card_tech);

        tvSports = (TextView)findViewById(R.id.txt_sports);
        tvPolitics = (TextView)findViewById(R.id.txt_politics);
        tvGeography = (TextView)findViewById(R.id.txt_geography);
        tvReligion = (TextView)findViewById(R.id.txt_religion);
        tvTech = (TextView)findViewById(R.id.txt_tech);

        Typeface custom_font_1 = Typeface.createFromAsset(getAssets(),  "fonts/Aller_Rg.ttf");
        tvSports.setTypeface(custom_font_1);
        tvPolitics.setTypeface(custom_font_1);
        tvGeography.setTypeface(custom_font_1);
        tvReligion.setTypeface(custom_font_1);
        tvTech.setTypeface(custom_font_1);

        cardSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizCategory.this, Sports.class));
            }
        });
        cardPolitics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizCategory.this, Politics.class));
            }
        });
        cardGeography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizCategory.this, Geography.class));
            }
        });
        cardReligion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizCategory.this, Religion.class));
            }
        });
        cardTech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizCategory.this, Technology.class));
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    private void alertBox(){
        new AlertDialog.Builder(this).setTitle("Welcome").setMessage("Let's take you through " +
                "the hot seat experience!")
                .setPositiveButton("Let's Go!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(QuizCategory.this, LandingActivity.class));
                finish();
            }
        }).create().show();
    }
}
