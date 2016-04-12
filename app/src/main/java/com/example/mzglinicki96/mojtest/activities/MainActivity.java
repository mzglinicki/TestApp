package com.example.mzglinicki96.mojtest.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mzglinicki96.mojtest.R;
import com.example.mzglinicki96.mojtest.database.DatabaseHelper;
import com.example.mzglinicki96.mojtest.fragments.GalleryFragment;
import com.example.mzglinicki96.mojtest.fragments.ParentFragment;
import com.example.mzglinicki96.mojtest.fragments.SplashFragment;
import com.example.mzglinicki96.mojtest.fragments.WeatherFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        createDrawerToggle();

        createFragment(R.id.nav_splash_screen);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void createDrawerToggle(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.nav_splash_screen:
                createFragment(R.id.nav_splash_screen);
                hideMenu();
                break;
            case R.id.nav_weather:
                createFragment(R.id.nav_weather);
                hideMenu();
                break;
            case R.id.nav_gallery:
                createFragment(R.id.nav_gallery);
                hideMenu();
                break;
            case R.id.nav_share:
                showInactiveToast();
                break;
            case R.id.nav_camera:
                showInactiveToast();
                break;
            case R.id.nav_settings:
                showInactiveToast();
        }
        return true;
    }

    private void hideMenu() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
    }

    private void showInactiveToast() {
        Toast.makeText(this, R.string.toast_non_active_choice, Toast.LENGTH_SHORT).show();
    }

    private void createFragment(int id){

        ParentFragment fragment;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (id){
            case R.id.nav_splash_screen:
                fragment = new SplashFragment();
                transaction.replace(R.id.place_holder, fragment);
                break;
            case R.id.nav_weather:
                fragment = new WeatherFragment();
                transaction.replace(R.id.place_holder, fragment);
                break;
            case R.id.nav_gallery:

                fragment = new GalleryFragment();
                transaction.replace(R.id.place_holder, fragment);
        }
        transaction.commit();
    }
}
