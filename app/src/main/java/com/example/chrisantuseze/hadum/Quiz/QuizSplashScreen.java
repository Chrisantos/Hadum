package com.example.chrisantuseze.hadum.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.chrisantuseze.hadum.MainActivity;
import com.example.chrisantuseze.hadum.R;

public class QuizSplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(QuizSplashScreen.this, LandingActivity.class));
                finish();
            }
        },2000);
    }

}
