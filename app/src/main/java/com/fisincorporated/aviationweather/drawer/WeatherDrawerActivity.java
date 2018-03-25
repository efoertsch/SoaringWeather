package com.fisincorporated.aviationweather.drawer;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.fisincorporated.aviationweather.R;
import com.fisincorporated.aviationweather.airports.AirportListActivity;
import com.fisincorporated.aviationweather.airportweather.AirportWeatherFragment;
import com.fisincorporated.aviationweather.app.DataLoading;
import com.fisincorporated.aviationweather.satellite.SatelliteImageFragment;
import com.fisincorporated.aviationweather.settings.SettingsActivity;

// Nav bar http://guides.codepath.com/android/fragment-navigation-drawer#setup-toolbar

public class WeatherDrawerActivity extends AppCompatActivity implements DataLoading {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.app_nav_drawer);

        drawerLayout = (DrawerLayout) findViewById(R.id.app_drawer_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerToggle = setupDrawerToggle();
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);

        loadingProgressBar = (ProgressBar) findViewById(R.id.activity_load_progress_bar);

        navigationView = (NavigationView) findViewById(R.id.app_weather_drawer);
        setupDrawerContent(navigationView);
        displayAirportWeather();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_menu_add_airport_codes:
                displayAirportList();
                break;
            case R.id.nav_menu_airport_weather:
                displayAirportWeather();
                break;
            case R.id.nav_menu_display_options:
                displaySettingsActivity();
                break;
            case R.id.nav_menu_satellite_images:
                displaySatelliteImages();
                break;
            default:
                displayAirportList();
        }

        drawerLayout.closeDrawers();

    }

    private void displayFragment(Fragment fragment) {
        // Replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().
                replace(R.id.app_frame_layout, fragment).
                commit();

    }


    private void displayAirportWeather() {
        AirportWeatherFragment fragment = new AirportWeatherFragment();
        displayFragment(fragment);

    }

    private void displayAirportList() {
        Intent i = new Intent(this, AirportListActivity.class);
        startActivity(i);
    }

    private void displaySettingsActivity() {
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }

    private void displaySatelliteImages() {
        SatelliteImageFragment fragment = new SatelliteImageFragment();
        displayFragment(fragment);
    }

    @Override
    public void loadRunning(final boolean dataLoading) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingProgressBar.setVisibility(dataLoading ? View.VISIBLE : View.GONE);
            }
        });
    }

}
