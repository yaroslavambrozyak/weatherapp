package com.example.yaroslav.weatherapp.presenter;


import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.example.yaroslav.weatherapp.App;
import com.example.yaroslav.weatherapp.api.OpenWeatherApi;
import com.example.yaroslav.weatherapp.view.LocationActivityFragment;
import com.example.yaroslav.weatherapp.view.LocationView;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class LocationPresenterImpl implements LocationPresenter {

    private LocationView view;
    private Application app;
    @Inject
    Retrofit retrofit;

    public LocationPresenterImpl(Application app) {
        this.app = app;
        ((App) app).getNetComponent().inject(this);
        retrofit.create(OpenWeatherApi.class);
    }

    @Override
    public void setView(LocationView view) {
        this.view = view;
    }

    @Override
    public void searchByCurrentLocation() {

    }

    @Override
    public void searchByCityName(String cityName) {

    }

}
