package com.example.chrisantuseze.hadum.Academia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.chrisantuseze.hadum.R;

public class Books extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spin1, spin2;
    private String[] seet = { "Engineeing","Agricultural & Bio-Resource Engineering",
            "Chemical Engineering","Civil Engineering","Electrical & Electronic Engineering",
            "Mechatronics Engineering","Mechanical Engineering",
            "Materials & Metallurgical Engineering","Petroleum Engineering","Polymer & Textile Engineering" };

    private String[] abe = {
            "Course"
    };
    private String[] che = {
            "Course"
    };
    private String[] cie = {
            "Course"
    };
    private String[] eee = {
            "Course"
    };
    private String[] mce = {
            "Course"
    };
    private String[] mee = {
            "Course"
    };
    private String[] mme = {
            "Course"
    };
    private String[] pet = {
            "Course"
    };
    private String[] pte = {
            "Course"
    };



    private ArrayAdapter de, le;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spin1 = (Spinner) findViewById(R.id.dept);
        spin2 = (Spinner) findViewById(R.id.course);

        spin1.setOnItemSelectedListener(this);

        ArrayAdapter fa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,seet);
        fa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(fa);




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
        String item = adapterView.getItemAtPosition(i).toString();

        switch (item){
            case "TuesAdapter":
                de = new ArrayAdapter(this,android.R.layout.simple_spinner_item,seet);
                de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                break;

            case "Agricultural & Bio-Resource Engineering":
                de = new ArrayAdapter(this,android.R.layout.simple_spinner_item,abe);
                de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                break;
            case "Chemical Engineering":
                de = new ArrayAdapter(this,android.R.layout.simple_spinner_item,che);
                de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                break;
            case "Civil Engineering":
                de = new ArrayAdapter(this,android.R.layout.simple_spinner_item,cie);
                de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                break;
            case "Electrical & Electronic Engineering":
                de = new ArrayAdapter(this,android.R.layout.simple_spinner_item,eee);
                de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                break;
            case "Mechatronics Engineering":
                de = new ArrayAdapter(this,android.R.layout.simple_spinner_item,mce);
                de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                break;
            case "Mechanical Engineering":
                de = new ArrayAdapter(this,android.R.layout.simple_spinner_item,mee);
                de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                break;
            case "Materials & Metallurgical Engineering":
                de = new ArrayAdapter(this,android.R.layout.simple_spinner_item,mme);
                de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                break;
            case "Petroleum Engineering":
                de = new ArrayAdapter(this,android.R.layout.simple_spinner_item,pet);
                de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                break;
            case "Polymer & Textile Engineering":
                de = new ArrayAdapter(this,android.R.layout.simple_spinner_item,pte);
                de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                break;
            default:
                de = new ArrayAdapter(this,android.R.layout.simple_spinner_item,abe);
                de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                break;
        }
        spin2.setAdapter(de);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
