package com.example.chrisantuseze.hadum.Nav;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.chrisantuseze.hadum.R;

public class Website extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);


        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(WebsiteFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(WebsiteFragment.ARG_ITEM_ID));
            WebsiteFragment fragment = new WebsiteFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.website_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            //onBackPressed();
            navigateUpTo(new Intent(Website.this, Website.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
