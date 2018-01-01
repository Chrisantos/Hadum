package com.example.chrisantuseze.hadum.Assistant.Result;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.chrisantuseze.hadum.Academia.Results;
import com.example.chrisantuseze.hadum.R;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import static com.example.chrisantuseze.hadum.Assistant.Result.ResultContract.ResultEntry.COLUMN_DESCRIPTION;
import static com.example.chrisantuseze.hadum.Assistant.Result.ResultContract.ResultEntry.TABLE_NAME;

public class GPACalculator extends AppCompatActivity {
    private Toolbar mToolbar;
    private SQLiteDatabase db;
    private ResultDatabaseHelper dbHelper;

    private Spinner spinLevel, spinSemester;
    private Spinner spinU1, spinU2, spinU3, spinU4, spinU5, spinU6, spinU7, spinU8, spinU9, spinU10;
    private Spinner spinG1, spinG2, spinG3, spinG4, spinG5, spinG6, spinG7, spinG8, spinG9, spinG10;

    private String[] semesters = { "First", "Second" };
    private String[] levels = { "100", "200", "300", "400", "500" };

    private String[] units = {"0", "1", "2", "3", "4"};
    private String[] grades = {"F", "E", "D", "C", "B", "A"};

    private ArrayAdapter arrayUnit, arrayGrade, arrayLevel, arraySemester;

    private int unit1, unit2, unit3, unit4, unit5, unit6, unit7, unit8, unit9, unit10;
    private int grade1, grade2, grade3, grade4, grade5, grade6, grade7, grade8, grade9, grade10;
    private String level, semester;
    private double unitGrade = 0.0;
    private double sumUnit = 0.0;
    private double GPA = 0.0;
    private double CGPA = 0.0;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpacalculator);
        mToolbar = (Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Calculator");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new ResultDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        initializeSpinners();
        setSpinnerUnitAdapter();
        setSpinnerGradeAdapter();
        setSpinnerLevelSemesterAdapter();

        unitsOnClick();
        gradesOnClick();
        levelSemesterOnCLick();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
            return true;
        }else if (id == R.id.save){
            unitGrade = unit1*grade1 + unit2*grade2 + unit3*grade3 + unit4*grade4 + unit5*grade5 + unit6*grade6
                    + unit7*grade7 + unit8*grade8 + unit9*grade9 + unit10*grade10;

            sumUnit = unit1 + unit2 + unit3 + unit4 + unit5 + unit6 + unit7 + unit8 + unit9 + unit10;
            if (sumUnit != 0.0){
                GPA = unitGrade/sumUnit;
                ResultSharedPreference sharedPref = new ResultSharedPreference(this);
                String prevCGPA = sharedPref.getKeyCgpa();
                String prevTotalUnits = sharedPref.getKeyTotalUnits();
                String prevTotalAggregate = sharedPref.getKeyTotalAggregate();
                double prevUnits = 0.0;
                double prevAggregate = 0.0;
                double currentSumUnits = sumUnit + prevUnits;
                double currentSumAggregate = unitGrade + prevAggregate;

                if (prevCGPA != null){
                    prevUnits = Double.parseDouble(prevTotalUnits);
                    prevAggregate = Double.parseDouble(prevTotalAggregate);
                    currentSumUnits = sumUnit + prevUnits;
                    currentSumAggregate = unitGrade + prevAggregate;
                    CGPA = currentSumAggregate/currentSumUnits;
                }
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                sharedPref.setKeyCgpa(""+decimalFormat.format(CGPA));
                sharedPref.setKeyTotalUnits("" +decimalFormat.format(currentSumUnits));
                sharedPref.setKeyTotalAggregate("" +decimalFormat.format(currentSumAggregate));

                description = level+ "L " +semester+" Semester GPA: "+decimalFormat.format(GPA);

                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_DESCRIPTION, description);
                db.insert(TABLE_NAME, null, contentValues);

                startActivity(new Intent(getApplicationContext(), Results.class));

                finish();
            }else{
                StyleableToast.makeText(getApplicationContext(), "Oops, error occurred"
                        , R.style.error).show();
            }


        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_result,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initializeSpinners(){
        spinLevel = (Spinner)findViewById(R.id.spinner_level);
        spinSemester = (Spinner)findViewById(R.id.spinner_semester);
        spinU1 = (Spinner)findViewById(R.id.unit1);
        spinU2 = (Spinner)findViewById(R.id.unit2);
        spinU3 = (Spinner)findViewById(R.id.unit3);
        spinU4 = (Spinner)findViewById(R.id.unit4);
        spinU5 = (Spinner)findViewById(R.id.unit5);
        spinU6 = (Spinner)findViewById(R.id.unit6);
        spinU7 = (Spinner)findViewById(R.id.unit7);
        spinU8 = (Spinner)findViewById(R.id.unit8);
        spinU9 = (Spinner)findViewById(R.id.unit9);
        spinU10 = (Spinner)findViewById(R.id.unit10);
        spinG1 = (Spinner)findViewById(R.id.grade1);
        spinG2 = (Spinner)findViewById(R.id.grade2);
        spinG3 = (Spinner)findViewById(R.id.grade3);
        spinG4 = (Spinner)findViewById(R.id.grade4);
        spinG5 = (Spinner)findViewById(R.id.grade5);
        spinG6 = (Spinner)findViewById(R.id.grade6);
        spinG7 = (Spinner)findViewById(R.id.grade7);
        spinG8 = (Spinner)findViewById(R.id.grade8);
        spinG9 = (Spinner)findViewById(R.id.grade9);
        spinG10 = (Spinner)findViewById(R.id.grade10);
    }
    private void setSpinnerUnitAdapter(){
        arrayUnit = new ArrayAdapter(this,android.R.layout.simple_spinner_item,units);
        arrayUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinU1.setAdapter(arrayUnit);
        spinU2.setAdapter(arrayUnit);
        spinU3.setAdapter(arrayUnit);
        spinU4.setAdapter(arrayUnit);
        spinU5.setAdapter(arrayUnit);
        spinU6.setAdapter(arrayUnit);
        spinU7.setAdapter(arrayUnit);
        spinU8.setAdapter(arrayUnit);
        spinU9.setAdapter(arrayUnit);
        spinU10.setAdapter(arrayUnit);
    }
    private void setSpinnerGradeAdapter(){
        arrayGrade = new ArrayAdapter(this,android.R.layout.simple_spinner_item,grades);
        arrayGrade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinG1.setAdapter(arrayGrade);
        spinG2.setAdapter(arrayGrade);
        spinG3.setAdapter(arrayGrade);
        spinG4.setAdapter(arrayGrade);
        spinG5.setAdapter(arrayGrade);
        spinG6.setAdapter(arrayGrade);
        spinG7.setAdapter(arrayGrade);
        spinG8.setAdapter(arrayGrade);
        spinG9.setAdapter(arrayGrade);
        spinG10.setAdapter(arrayGrade);
    }
    private void setSpinnerLevelSemesterAdapter(){
        arrayLevel = new ArrayAdapter(this,android.R.layout.simple_spinner_item,levels);
        arrayLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinLevel.setAdapter(arrayLevel);

        arraySemester = new ArrayAdapter(this,android.R.layout.simple_spinner_item,semesters);
        arraySemester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSemester.setAdapter(arraySemester);
    }
    private void unitsOnClick(){
        spinU1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit1 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinU2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit2 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinU3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit3 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinU4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit4 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinU5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit5 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinU6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit6 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinU7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit7 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinU8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit8 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinU9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit9 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinU10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit10 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void gradesOnClick(){
        spinG1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grade1 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinG2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grade2 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinG3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grade3 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinG4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grade4 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinG5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grade5 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinG6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grade6 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinG7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grade7 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinG8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grade8 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinG9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grade9 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinG10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grade10 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void levelSemesterOnCLick(){
        spinLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                level = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semester = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
