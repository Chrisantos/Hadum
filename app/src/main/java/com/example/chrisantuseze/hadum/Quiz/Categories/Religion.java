package com.example.chrisantuseze.hadum.Quiz.Categories;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chrisantuseze.hadum.R;

public class Religion extends AppCompatActivity {
    private TextView quest, opt1, opt2, opt3, opt4, status, score, questNum;
    private RadioButton radio1, radio2, radio3, radio4;
    private Button btNext, btnOk;
    private Question question;
    private String mAnswer;
    private int mScore = 0;
    private int num = 0;
    private int noQuest;
    private String correctAnswer = "Correct!";
    private String wrongAnswer = "Wrong!";
    QuizDB quizDB;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_religion);
        mToolbar = (Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Religion");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        question = new Question();
        quizDB = new QuizDB(this);

        noQuest = question.getNoQuestions();

        quest = (TextView)findViewById(R.id.questions);
        questNum = (TextView)findViewById(R.id.questionsNum);
        opt1 = (TextView)findViewById(R.id.option1);
        opt2 = (TextView)findViewById(R.id.option2);
        opt3 = (TextView)findViewById(R.id.option3);
        opt4 = (TextView)findViewById(R.id.option4);
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton)findViewById(R.id.radio2);
        radio3 = (RadioButton)findViewById(R.id.radio3);
        radio4 = (RadioButton)findViewById(R.id.radio4);

        status = (TextView)findViewById(R.id.status);
        score = (TextView)findViewById(R.id.score);
        btNext = (Button)findViewById(R.id.nextQuestion);
        //btnOk = (Button)findViewById(R.id.btnOk);

        radio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                radio2.setChecked(false);
                radio3.setChecked(false);
                radio4.setChecked(false);
            }
        });
        radio2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                radio1.setChecked(false);
                radio3.setChecked(false);
                radio4.setChecked(false);
            }
        });
        radio3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                radio2.setChecked(false);
                radio1.setChecked(false);
                radio4.setChecked(false);
            }
        });
        radio4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                radio2.setChecked(false);
                radio3.setChecked(false);
                radio1.setChecked(false);
            }
        });



        updateQuestion(num);
        score.setText(String.valueOf(mScore));
        num++;
        questNum.setText(""+num);

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                questNum.setText(""+num);

                if (radio1.isChecked()){
                    if (opt1.getText() == mAnswer){
                        mScore++;
                        status.setText("Correct Answer");
                        status.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    }else {
                        status.setText("Wrong Answer");
                        status.setTextColor(getResources().getColor(R.color.notif));
                    }
                    radio1.setChecked(false);
                    updateQuestion(num);
                    status.setText("");
                    score.setText(String.valueOf(mScore));

                }else if (radio2.isChecked()){
                    if (opt2.getText() == mAnswer){
                        mScore++;
                        status.setText("Correct Answer");
                    }else {
                        status.setText("Wrong Answer");
                    }
                    radio2.setChecked(false);

                    updateQuestion(num);
                    status.setText("");
                    score.setText(String.valueOf(mScore));

                }else if (radio3.isChecked()){
                    if (opt3.getText() == mAnswer){
                        mScore++;
                        status.setText("Correct Answer");

                    }else {
                        status.setText("Wrong Answer");
                    }
                    radio3.setChecked(false);
                    updateQuestion(num);
                    status.setText("");
                    score.setText(String.valueOf(mScore));

                }else if (radio4.isChecked()){
                    if (opt4.getText() == mAnswer){
                        mScore++;
                        status.setText("Correct Answer");

                    }else {
                        status.setText("Wrong Answer");
                    }
                    radio4.setChecked(false);
                    updateQuestion(num);
                    status.setText("");
                    score.setText(String.valueOf(mScore));

                }

            }
        });

//        btnOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (radio1.isChecked()){
//                    if (opt1.getText() == mAnswer){
//                        mScore++;
//                        status.setText(correctAnswer);
//                        status.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//                        score.setText(String.valueOf(mScore));
//                    }else {
//                        status.setText(wrongAnswer);
//                        status.setTextColor(getResources().getColor(R.color.notif));
//                    }
//
//
//                }else if (radio2.isChecked()){
//                    if (opt2.getText() == mAnswer){
//                        mScore++;
//                        status.setText(correctAnswer);
//                        status.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//                        score.setText(String.valueOf(mScore));
//                    }else {
//                        status.setText(wrongAnswer);
//                        status.setTextColor(getResources().getColor(R.color.notif));
//                    }
//
//
//                }else if (radio3.isChecked()){
//                    if (opt3.getText() == mAnswer){
//                        mScore++;
//                        status.setText(correctAnswer);
//                        status.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//                        score.setText(String.valueOf(mScore));
//
//                    }else {
//                        status.setText(wrongAnswer);
//                        status.setTextColor(getResources().getColor(R.color.notif));
//                    }
//
//
//                }else if (radio4.isChecked()){
//                    if (opt4.getText() == mAnswer){
//                        mScore++;
//                        status.setText(correctAnswer);
//                        status.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//                        score.setText(String.valueOf(mScore));
//
//                    }else {
//                        status.setText(wrongAnswer);
//                        status.setTextColor(getResources().getColor(R.color.notif));
//                    }
//
//
//                }
//
//            }
//        });
//
//
//        btNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (status.getText().toString().equals(correctAnswer)){
//                    num++;
//                    questNum.setText(""+(num));
//                    if (radio1.isChecked()){
//                        radio1.setChecked(false);
//                        updateQuestion(num);
//                        status.setText("");
//                        score.setText(String.valueOf(mScore));
//                    }else if(radio2.isChecked()){
//                        radio2.setChecked(false);
//                        updateQuestion(num);
//                        status.setText("");
//                        score.setText(String.valueOf(mScore));
//                    }else if(radio3.isChecked()){
//                        radio3.setChecked(false);
//                        updateQuestion(num);
//                        status.setText("");
//                        score.setText(String.valueOf(mScore));
//                    }else if(radio4.isChecked()){
//                        radio4.setChecked(false);
//                        updateQuestion(num);
//                        status.setText("");
//                        score.setText(String.valueOf(mScore));
//                    }
//
//
//                }else {
//                    Toast.makeText(getApplicationContext(), "Cannot Proceed!", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });


    }

    private void updateQuestion(int num){
        if (num == (noQuest)){
            if (mScore > quizDB.getKeyReligion())
                quizDB.setKeyReligion(mScore);
            new AlertDialog.Builder(this).setTitle("End Of Game")
                    .setMessage("You scored "+mScore)
                    .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(Religion.this, Religion.class));
                            finish();
                        }
                    }).setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
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
            opt4.setText(question.getChoice4(num));
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
