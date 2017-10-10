package com.example.yaroslav.weatherapp.presenter;


import android.app.Application;

import com.example.yaroslav.weatherapp.App;
import com.example.yaroslav.weatherapp.api.OpenWeatherApi;
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
        ((App)app).getNetComponent().inject(this);
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
    public void searchByCityName() {

    }

    private Coordinates getCoordinates{
        
    }

    private class Coordinates{
        double lon;
        double lan;
    }
}
