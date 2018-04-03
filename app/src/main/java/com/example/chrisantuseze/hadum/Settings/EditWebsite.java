package com.example.chrisantuseze.hadum.Settings;

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

import com.example.chrisantuseze.hadum.MainActivity;
import com.example.chrisantuseze.hadum.R;
import com.example.chrisantuseze.hadum.UserInfo;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class EditWebsite extends AppCompatActivity {
    private EditText mEditWebsite;
    private Button mBtnSave;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_website);
        mToolbar = (Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Edit School Website");

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEditWebsite = (EditText)findViewById(R.id.edit_website);
        mBtnSave = (Button)findViewById(R.id.btn_edit_website);

        Typeface custom_font_1 = Typeface.createFromAsset(getAssets(),  "fonts/Aller_Rg.ttf");
        mEditWebsite.setTypeface(custom_font_1);
        mBtnSave.setTypeface(custom_font_1);
        final UserInfo userInfo = new UserInfo(this);

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String web = mEditWebsite.getText().toString();

                    if (!TextUtils.isEmpty(web)){
                        StyleableToast.makeText(getApplicationContext(), "Changes saved", R.style.success).show();
                        //changeName(name);
                        userInfo.setKeyWebsite(web);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }else{
                        StyleableToast.makeText(getApplicationContext(), "Field cannot be empty!", R.style.error).show();
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
