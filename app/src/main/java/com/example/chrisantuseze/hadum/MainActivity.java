package com.example.chrisantuseze.hadum;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.chrisantuseze.hadum.Nav.Academia;
import com.example.chrisantuseze.hadum.Nav.Feedback;
import com.example.chrisantuseze.hadum.Nav.Notifications;
import com.example.chrisantuseze.hadum.Nav.Profile;
import com.example.chrisantuseze.hadum.Nav.Quiz;
import com.example.chrisantuseze.hadum.Nav.Settings;
import com.example.chrisantuseze.hadum.Timetablee.Timetable;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Timetable");
        Timetable timetable = new Timetable();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, timetable).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.timetable) {
            setTitle("Timetable");
            Timetable timetable = new Timetable();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, timetable).commit();

        }else if (id == R.id.notifications) {
            setTitle("Notifs");
            Notifications notifications = new Notifications();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, notifications).commit();

        } else if (id == R.id.academia) {
            setTitle("Academia");
            Academia academia = new Academia();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, academia).commit();

        } else if (id == R.id.quiz) {
            setTitle("Play Quizz");
            Quiz quiz = new Quiz();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, quiz).commit();

        } else if (id == R.id.profile) {
            setTitle("Profile");
            Profile profile = new Profile();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, profile).commit();

        } else if (id == R.id.settings) {
            setTitle("Settings");
            Settings settings = new Settings();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, settings).commit();

        } else if (id == R.id.feedback) {
            setTitle("Help and Feedback");
            Feedback feedback = new Feedback();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, feedback).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
