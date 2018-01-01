package com.example.chrisantuseze.hadum.Academia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chrisantuseze.hadum.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Books extends AppCompatActivity {

    private Spinner spinDept, spinCourse;
    private String[] seet = { "ABE", "CHE","CIE","EEE", "MCE","MEE", "MME","PET","PTE" };

    private String[] abe = {
            "MTH101", "MTH102", "ABE305", "ENG305"
    };
    private String[] che = {
            "Course"
    };
    private String[] cie = {
            "Course"
    };
    private String[] eee = {
            "MTH101", "MTH102", "EEE202", "EEE204", "ENG305"
    };
    private String[] mce = {
            "Course"
    };
    private String[] mee = {
            "MTH101", "MTH102", "MEE202", "MEE204", "ENG305"
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



    private ArrayAdapter de;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private ProgressBar progressBar;
    private Product product;
    private List<Product> productList;
    private String item = "";

    private static final String URL_PRODUCTS = "http://192.168.43.242/hadum/book_api.php";

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        mToolbar = (Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Books");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fab = (FloatingActionButton)findViewById(R.id.button);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        spinDept = (Spinner) findViewById(R.id.spinnerdept);
        spinCourse = (Spinner) findViewById(R.id.spinnercourse);

        ArrayAdapter fa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,seet);
        fa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDept.setAdapter(fa);

        spinDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();

                switch (item){
                    case "ABE":
                        de = new ArrayAdapter(Books.this,android.R.layout.simple_spinner_item,abe);
                        de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "CHE":
                        de = new ArrayAdapter(Books.this,android.R.layout.simple_spinner_item,che);
                        de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "CIE":
                        de = new ArrayAdapter(Books.this,android.R.layout.simple_spinner_item,cie);
                        de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "EEE":
                        de = new ArrayAdapter(Books.this,android.R.layout.simple_spinner_item,eee);
                        de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "MCE":
                        de = new ArrayAdapter(Books.this,android.R.layout.simple_spinner_item,mce);
                        de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "MEE":
                        de = new ArrayAdapter(Books.this,android.R.layout.simple_spinner_item,mee);
                        de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "MME":
                        de = new ArrayAdapter(Books.this,android.R.layout.simple_spinner_item,mme);
                        de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "PET":
                        de = new ArrayAdapter(Books.this,android.R.layout.simple_spinner_item,pet);
                        de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "PTE":
                        de = new ArrayAdapter(Books.this,android.R.layout.simple_spinner_item,pte);
                        de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        break;
                    default:
                        de = new ArrayAdapter(Books.this,android.R.layout.simple_spinner_item,abe);
                        de.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        break;
                }
                spinCourse.setAdapter(de);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                item = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProducts(item);
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        productList = new ArrayList<>();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    private void loadProducts(final String course) {

        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
        * */
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Product(product.getString(course)
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            BookAdapter adapter = new BookAdapter(Books.this, productList);
                            recyclerView.setAdapter(adapter);
                            progressBar.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }


}
