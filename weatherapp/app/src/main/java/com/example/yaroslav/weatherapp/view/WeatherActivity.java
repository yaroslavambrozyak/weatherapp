package com.example.yaroslav.weatherapp.view;

import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.widget.ScrollView;

import com.example.yaroslav.weatherapp.Constants;
import com.example.yaroslav.weatherapp.R;

public class WeatherActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        WeatherActivityFragment fragment = new WeatherActivityFragment();
        Bundle bundle = new Bundle();
        boolean isByCity = getIntent().getBooleanExtra(Constants.BY_CITY, false);
        bundle.putBoolean(Constants.BY_CITY, isByCity);
        if (isByCity) {
            bundle.putString(Constants.CITY_NAME, getIntent().getStringExtra(Constants.CITY_NAME));
        } else {
            bundle.putDouble(Constants.LONGITUDE, getIntent().getDoubleExtra(Constants.LONGITUDE, 0));
            bundle.putDouble(Constants.LATITUDE, getIntent().getDoubleExtra(Constants.LATITUDE, 0));
        }
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();

    }

}
