package com.example.yaroslav.weatherapp.view;


import com.example.yaroslav.weatherapp.model.WeatherForecast;

import java.util.List;

public interface WeatherView {

    void setCurrentWeatherIcon(String icon);

    void setCurrentDescription(String description);

    void setCurrentCountry(String country);

    void setCurrentTemperature(String temperature);

    void setCurrentTemperatureMin(String temperatureMin);

    void setCurrentTemperatureMax(String temperatureMax);

    void setCurrentClouds(String clouds);

    void setCurrentWindSpeed(String windSpeed);

    void setCurrentRain(String rain);

    void setCurrentData(String data);

    void setDayWeatherData(List<WeatherForecast> data);

    void setTimeWeatherData(List<WeatherForecast> data);
}
