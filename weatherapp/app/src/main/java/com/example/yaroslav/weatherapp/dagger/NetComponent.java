package com.example.yaroslav.weatherapp.dagger;

import com.example.yaroslav.weatherapp.presenter.LocationPresenterImpl;
import com.example.yaroslav.weatherapp.presenter.WeatherPresenter;
import com.example.yaroslav.weatherapp.presenter.WeatherPresenterImpl;
import com.example.yaroslav.weatherapp.view.LocationActivityFragment;
import com.example.yaroslav.weatherapp.view.WeatherActivityFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class,PresenterModule.class})
public interface NetComponent {

    void inject(LocationActivityFragment view);
    void inject(WeatherActivityFragment view);
    void inject(LocationPresenterImpl presenter);
    void inject(WeatherPresenterImpl presenter);
}
