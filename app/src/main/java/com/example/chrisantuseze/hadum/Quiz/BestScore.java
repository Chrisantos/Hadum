package com.example.chrisantuseze.hadum.Quiz;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.chrisantuseze.hadum.Quiz.Categories.QuizDB;
import com.example.chrisantuseze.hadum.R;

public class BestScore extends AppCompatActivity {
    private TextView tvSports, tvPolitics, tvGeo, tvReligion, tvTech;
    private TextView tvNameSports, tvNamePolitics, tvNameGeo, tvNameReligion, tvNameTech;
    QuizDB quizDB;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_score);
        mToolbar = (Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Best Score");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        quizDB = new QuizDB(this);

        tvSports = (TextView)findViewById(R.id.sportscore);
        tvPolitics = (TextView)findViewById(R.id.politicsscore);
        tvGeo = (TextView)findViewById(R.id.geographyscore);
        tvReligion = (TextView)findViewById(R.id.religionscore);
        tvTech = (TextView)findViewById(R.id.technologyscore);

        tvNameSports = (TextView)findViewById(R.id.tsports);
        tvNamePolitics = (TextView)findViewById(R.id.tpolitics);
        tvNameGeo = (TextView)findViewById(R.id.tgeo);
        tvNameReligion = (TextView)findViewById(R.id.treligion);
        tvNameTech = (TextView)findViewById(R.id.ttech);

        Typeface custom_font_1 = Typeface.createFromAsset(getAssets(),  "fonts/Aller_Rg.ttf");
        tvNameSports.setTypeface(custom_font_1);
        tvNamePolitics.setTypeface(custom_font_1);
        tvNameGeo.setTypeface(custom_font_1);
        tvNameReligion.setTypeface(custom_font_1);
        tvNameTech.setTypeface(custom_font_1);

        int sports = quizDB.getKeySports();
        int politics = quizDB.getKeyPolitics();
        int geo = quizDB.getKeyGeo();
        int religion = quizDB.getKeyReligion();
        int tech = quizDB.getKeyTech();

        tvSports.setText(""+sports);
        tvPolitics.setText(""+politics);
        tvGeo.setText(""+geo);
        tvReligion.setText(""+religion);
        tvTech.setText(""+tech);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
