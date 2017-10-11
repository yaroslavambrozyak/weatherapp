package com.example.yaroslav.weatherapp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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
        bundle.putString(Constants.CITY_NAME,getIntent().getStringExtra(Constants.CITY_NAME));
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();

    }

}
