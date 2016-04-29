package com.example.mzglinicki96.mojtest.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mzglinicki96.mojtest.R;
import com.example.mzglinicki96.mojtest.fragments.CityFragment;
import com.example.mzglinicki96.mojtest.fragments.GalleryFragment;
import com.example.mzglinicki96.mojtest.fragments.SplashFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String FRAGMENT_KEY = "fragment";
    private Fragment fragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDrawerToggle();

        if (savedInstanceState != null) {
            fragment = getSupportFragmentManager().getFragment(savedInstanceState, FRAGMENT_KEY);
        } else {
            createFragment(R.id.nav_splash_screen);
        }

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, FRAGMENT_KEY, fragment);
    }

    public void createDrawerToggle() {

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {

            getSupportFragmentManager().popBackStack();
            return;
        }
        super.onBackPressed();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
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
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
    }

    private void showInactiveToast() {
        Toast.makeText(this, R.string.toast_non_active_choice, Toast.LENGTH_SHORT).show();
    }

    private void createFragment(final int id) {

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (id) {
            case R.id.nav_splash_screen:
                fragment = new SplashFragment();
                transaction.replace(R.id.place_holder, fragment);
                break;
            case R.id.nav_weather:
                fragment = new CityFragment();
                transaction.replace(R.id.place_holder, fragment);
                break;
            case R.id.nav_gallery:
                fragment = new GalleryFragment();
                transaction.replace(R.id.place_holder, fragment);
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
