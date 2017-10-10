package com.example.yaroslav.weatherapp.dagger;


import android.app.Application;

import com.example.yaroslav.weatherapp.presenter.LocationPresenter;
import com.example.yaroslav.weatherapp.presenter.LocationPresenterImpl;
import com.example.yaroslav.weatherapp.presenter.WeatherPresenter;
import com.example.yaroslav.weatherapp.presenter.WeatherPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    public WeatherPresenter provideWeatherPresenter(){
        return new WeatherPresenterImpl();
    }

    @Provides
    @Singleton
    public LocationPresenter provideLocationPresenter(Application app){
        return new LocationPresenterImpl(app);
    }
}
