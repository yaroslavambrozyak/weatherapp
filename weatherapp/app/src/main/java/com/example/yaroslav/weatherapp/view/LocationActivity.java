package com.example.yaroslav.weatherapp.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.yaroslav.weatherapp.App;
import com.example.yaroslav.weatherapp.R;
import com.example.yaroslav.weatherapp.presenter.WeatherPresenter;
import com.example.yaroslav.weatherapp.presenter.WeatherPresenterImpl;

import javax.inject.Inject;

public class LocationActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void bb(){

    }

}
