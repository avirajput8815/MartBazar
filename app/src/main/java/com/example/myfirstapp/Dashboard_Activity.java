package com.example.myfirstapp;


import android.database.sqlite.SQLiteDatabase;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard_Activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    public SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dashboard_activity);

        database=Database_Handler.getInstance(Dashboard_Activity.this).getWritableDatabase();
        // call database from database_handler


        bottomNavigationView = findViewById(R.id.naviagtion);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new Home_Fragment()).commit();

        // disable dark mode from dashboard activity
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            // variable
            Fragment selectedFragment = null;

            // from menu id get ki hai
            int itemId = item.getItemId();


            if (itemId == R.id.nave_home) {
                selectedFragment = new Home_Fragment();
            } else if (itemId == R.id.nave_cart) {
                selectedFragment = new Cart_Fragment();
            } else if (itemId == R.id.nave_about) {
                selectedFragment = new About_Fragment();
            }

            // It will help to replace the // one fragment to other.
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, selectedFragment).commit();
            }
            return true;

        });

    }

}







