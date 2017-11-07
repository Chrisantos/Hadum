package com.example.chrisantuseze.hadum.Nav;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.chrisantuseze.hadum.MainActivity;
import com.example.chrisantuseze.hadum.R;

public class Quiz extends Fragment {

    private TextView quest, opt1, opt2, opt3, opt4, status, score;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    private Button btNext;
    private Question question;
    private String mAnswer;
    private int mScore = 0;
    private int num = 0;
    private int noQuest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        alertBox();
        question = new Question();

        noQuest = question.getNoQuestions();

        quest = (TextView)view.findViewById(R.id.question);
        opt1 = (TextView)view.findViewById(R.id.option1);
        opt2 = (TextView)view.findViewById(R.id.option2);
        opt3 = (TextView)view.findViewById(R.id.option3);
        opt4 = (TextView)view.findViewById(R.id.option4);
        checkBox1 = (CheckBox) view.findViewById(R.id.check1);
        checkBox2 = (CheckBox) view.findViewById(R.id.check2);
        checkBox3 = (CheckBox) view.findViewById(R.id.check3);
        checkBox4 = (CheckBox) view.findViewById(R.id.check4);

        status = (TextView)view.findViewById(R.id.status);
        score = (TextView)view.findViewById(R.id.score);
        btNext = (Button)view.findViewById(R.id.button);


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




        return view;
    }
    private void alertBox(){
        new AlertDialog.Builder(getContext()).setTitle("Welcome").setMessage("Do you think you know much" +
                " about Nigeria & FUTO in particular?\nThen take our quiz")
                .setPositiveButton("Let's Go!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Notifications notifications = new Notifications();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, notifications).commit();
            }
        }).create().show();
    }

    private void updateQuestion(int num){
        if (num == (noQuest-1)){
            new AlertDialog.Builder(getContext()).setTitle("End Of Game")
                    .setMessage("You scored "+mScore)
                    .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Quiz quiz = new Quiz();
                            FragmentTransaction fragmentTransaction = getFragmentManager()
                                    .beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, quiz).commit();
                        }
                    }).setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                   startActivity(new Intent(getContext(), MainActivity.class));
                }
            }).create().show();
        }
        quest.setText(question.getQuestion(num));
        opt1.setText(question.getChoice1(num));
        opt2.setText(question.getChoice2(num));
        opt3.setText(question.getChoice3(num));
        opt4.setText(question.getChoice4(num));

        mAnswer = question.getAnswer(num);
    }

}
