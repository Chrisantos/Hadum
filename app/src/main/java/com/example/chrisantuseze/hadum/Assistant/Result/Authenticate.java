package com.example.chrisantuseze.hadum.Assistant.Result;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chrisantuseze.hadum.Academia.Results;
import com.example.chrisantuseze.hadum.R;
import com.example.chrisantuseze.hadum.UserInfo;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class Authenticate extends AppCompatActivity {
    private TextView mTextView;
    private EditText mRegNo;
    private Button mBtnProceed;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        mToolbar = (Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Authentication");

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTextView = (TextView)findViewById(R.id.confirm_tv);
        mBtnProceed = (Button)findViewById(R.id.proceed);
        mRegNo = (EditText)findViewById(R.id.regno);

        Typeface custom_font_1 = Typeface.createFromAsset(getAssets(),  "fonts/Aller_Rg.ttf");
        mTextView.setTypeface(custom_font_1);
        mBtnProceed.setTypeface(custom_font_1);
        mRegNo.setTypeface(custom_font_1);

        UserInfo userInfo = new UserInfo(this);
        final String regno = userInfo.getKeyRegno();

        mBtnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edRegno = mRegNo.getText().toString();
                if (!TextUtils.isEmpty(regno) && !TextUtils.isEmpty(edRegno)){
                    if (regno.equals(edRegno)){
                        StyleableToast.makeText(getApplicationContext(), "Access Granted!",
                                R.style.success).show();

                        startActivity(new Intent(getApplicationContext(), Results.class));
                        finish();
                    }else{
                        StyleableToast.makeText(getApplicationContext(), "Invalid Registration Number"
                                , R.style.error).show();
                    }
                }else{
                    StyleableToast.makeText(getApplicationContext(), "Field Cannot be empty!"
                            , R.style.error).show();
                }
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
