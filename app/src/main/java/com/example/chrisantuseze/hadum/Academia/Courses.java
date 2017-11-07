package com.example.chrisantuseze.hadum.Academia;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chrisantuseze.hadum.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Courses extends AppCompatActivity{

    private Spinner spin1, spin2, spin3;
    private String[] seet = { "Department","Agricultural & Bio-Resource Engineering",
            "Chemical Engineering","Civil Engineering","Electrical & Electronic Engineering",
            "Mechatronics Engineering","Mechanical Engineering",
            "Materials & Metallurgical Engineering","Petroleum Engineering","Polymer & Textile Engineering" };
    private String[] dept = {
            "ABE", "CHE", "CIE", "EEE", "MCE", "MEE", "MME", "PTE", "PET"
    };

    private String[] semester = { "sem1","sem2" };
    private String[] level = { "1","2","3","4","5" };

    private ArrayAdapter de;
    private TextView tvResult;
    private FloatingActionButton btGet;
    private String sem = " ";
    private String yLevel = "";
    private String mDept = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spin1 = (Spinner) findViewById(R.id.d_s_c);
        spin2 = (Spinner) findViewById(R.id.s_s_c);
        spin3 = (Spinner) findViewById(R.id.l_s_c);

        tvResult = (TextView)findViewById(R.id.result);
        btGet = (FloatingActionButton) findViewById(R.id.button);

        de = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dept);
        de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(de);

        ArrayAdapter se = new ArrayAdapter(this,android.R.layout.simple_spinner_item,semester);
        se.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(se);

        ArrayAdapter le = new ArrayAdapter(this,android.R.layout.simple_spinner_item,level);
        se.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3.setAdapter(le);

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mDept = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sem = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                yLevel = adapterView.getItemAtPosition(i).toString();

                Log.d("Chrisantus", yLevel);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncFetch("", sem, yLevel, mDept).execute();
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

    private class AsyncFetch extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(Courses.this);
        HttpURLConnection conn;
        URL url = null;
        String searchQuery;
        String semester, levl, dept;

        public AsyncFetch(String searchQuery, String semester, String levl, String dept){
            this.searchQuery=searchQuery;
            this.semester = semester;
            this.levl = levl;
            this.dept = dept;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("http://192.168.43.242/hadum/my_api.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                //conn.setReadTimeout(READ_TIMEOUT);
                //conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput to true as we send and recieve data
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // add parameter to our above url
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("searchQuery", searchQuery);
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    Log.d("Chrisantus", result.toString());
                    return result.toString();

                } else {
                    return("Connection error");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread
            pdLoading.dismiss();

            pdLoading.dismiss();
            if(result.equals("no rows")) {
                Toast.makeText(Courses.this, "No Results found for entered query", Toast.LENGTH_LONG).show();
            }else{

                try {
                    Log.d("Chrisantus Eze", "["+result+"]");
                    Log.d("Chris Eze", result);
                    //JSONObject jObj = new JSONObject(result);   //jArray can represent item at row 1 for instance

                    JSONArray jsonArray = new JSONArray(result);
                    int len = jsonArray.length();
                    for (int i=0; i<len; i++){
                        JSONObject jObj = jsonArray.getJSONObject(i);
                        String sem = jObj.getString(semester);
                        String level = jObj.getString("level");
                        String dpt = jObj.getString("department");
                        if (level.equals(levl) && dpt.equals(dept)){
                            tvResult.setText(sem);
                        }
                    }



                } catch (JSONException e) {
                    // You to understand what actually error is and handle it appropriately
                    Toast.makeText(Courses.this, e.toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(Courses.this, result.toString(), Toast.LENGTH_LONG).show();
                }

            }

        }

    }
}
