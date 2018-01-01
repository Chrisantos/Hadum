package com.example.chrisantuseze.hadum.Nav;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chrisantuseze.hadum.MainActivity;
import com.example.chrisantuseze.hadum.Quiz.Categories.Question;
import com.example.chrisantuseze.hadum.R;

public class Quiz extends AppCompatActivity {

    private TextView quest, opt1, opt2, opt3, opt4, status, score;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    private Button btNext;
    private Question question;
    private String mAnswer;
    private int mScore = 0;
    private int num = 0;
    private int noQuest;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mToolbar = (Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Quiz");

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        alertBox();
        question = new Question();

        noQuest = question.getNoQuestions();

        quest = (TextView)findViewById(R.id.question);
        opt1 = (TextView)findViewById(R.id.option1);
        opt2 = (TextView)findViewById(R.id.option2);
        opt3 = (TextView)findViewById(R.id.option3);
        opt4 = (TextView)findViewById(R.id.option4);
        checkBox1 = (CheckBox)findViewById(R.id.check1);
        checkBox2 = (CheckBox)findViewById(R.id.check2);
        checkBox3 = (CheckBox)findViewById(R.id.check3);
        checkBox4 = (CheckBox)findViewById(R.id.check4);

        status = (TextView)findViewById(R.id.status);
        score = (TextView)findViewById(R.id.score);
        btNext = (Button)findViewById(R.id.button);


        updateQuestion(num);
        score.setText(String.valueOf(mScore));

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;


                if (checkBox1.isChecked()){
                    if (opt1.getText() == mAnswer){
                        mScore++;
                        status.setText("Correct Answer");
                        status.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    }else {
                        status.setText("Wrong Answer");
                        status.setTextColor(getResources().getColor(R.color.notif));
                    }
                    checkBox1.setChecked(false);
                    updateQuestion(num);
                    status.setText("");
                    score.setText(String.valueOf(mScore));

                }else if (checkBox2.isChecked()){
                    if (opt2.getText() == mAnswer){
                        mScore++;
                        status.setText("Correct Answer");
                    }else {
                        status.setText("Wrong Answer");
                    }
                    checkBox2.setChecked(false);

                    updateQuestion(num);
                    status.setText("");
                    score.setText(String.valueOf(mScore));

                }else if (checkBox3.isChecked()){
                    if (opt3.getText() == mAnswer){
                        mScore++;
                        status.setText("Correct Answer");

                    }else {
                        status.setText("Wrong Answer");
                    }
                    checkBox3.setChecked(false);
                    updateQuestion(num);
                    status.setText("");
                    score.setText(String.valueOf(mScore));

                }else if (checkBox4.isChecked()){
                    if (opt4.getText() == mAnswer){
                        mScore++;
                        status.setText("Correct Answer");

                    }else {
                        status.setText("Wrong Answer");
                    }
                    checkBox4.setChecked(false);
                    updateQuestion(num);
                    status.setText("");
                    score.setText(String.valueOf(mScore));

                }

            }
        });


    }
    private void alertBox(){
        new AlertDialog.Builder(this).setTitle("Welcome").setMessage("Do you think you know much" +
                " about Nigeria & FUTO in particular?\nThen take our quiz")
                .setPositiveButton("Let's Go!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Quiz.this, MainActivity.class));
                finish();
            }
        }).create().show();
    }

    private void updateQuestion(int num){
        if (num == (noQuest-1)){
            new AlertDialog.Builder(this).setTitle("End Of Game")
                    .setMessage("You scored "+mScore)
                    .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                           startActivity(new Intent(Quiz.this, Quiz.class));
                        }
                    }).setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(Quiz.this, MainActivity.class));
                }
            }).create().show();
        }
        if (question.getQuestion(num).equals("")){
            Toast.makeText(this, "Questions exhausted!", Toast.LENGTH_SHORT).show();
        }else{
            quest.setText(question.getQuestion(num));
        }

        if (question.getChoice1(num).equals("")){
            Toast.makeText(this, "Questions exhausted!", Toast.LENGTH_SHORT).show();
        }else{
            opt1.setText(question.getChoice1(num));
        }

        if (question.getChoice2(num).equals("")){
            Toast.makeText(this, "Questions exhausted!", Toast.LENGTH_SHORT).show();
        }else{
            opt2.setText(question.getChoice2(num));
        }

        if (question.getChoice3(num).equals("")){
            Toast.makeText(this, "Questions exhausted!", Toast.LENGTH_SHORT).show();
        }else{
            opt3.setText(question.getChoice3(num));
        }

        if (question.getChoice4(num).equals("")){
            Toast.makeText(this, "Questions exhausted!", Toast.LENGTH_SHORT).show();
        }else{
            opt4.setText(question.getChoice3(num));
        }

        mAnswer = question.getAnswer(num);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
