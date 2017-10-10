package com.example.yaroslav.weatherapp.presenter;


import com.example.yaroslav.weatherapp.view.WeatherView;

public interface WeatherPresenter {
    void setView(WeatherView view);
    void loadWeather(String city);
}
