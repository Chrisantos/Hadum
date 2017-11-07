package com.example.chrisantuseze.hadum.Academia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.chrisantuseze.hadum.R;

public class PastQnA extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private String[] course = {
            "Course","PHY101","PHY102","CHM101","CHM102","MTH101","MTH102","BIO103"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_qna);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner = (Spinner) findViewById(R.id.qna);

        ArrayAdapter fa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,course);
        fa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(fa);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
