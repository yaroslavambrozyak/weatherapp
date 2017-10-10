package com.example.yaroslav.weatherapp.presenter;


import android.app.Application;

import com.example.yaroslav.weatherapp.App;
import com.example.yaroslav.weatherapp.api.OpenWeatherApi;
import com.example.yaroslav.weatherapp.model.OpenWeather;
import com.example.yaroslav.weatherapp.model.WeatherForecast;
import com.example.yaroslav.weatherapp.view.WeatherView;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherPresenterImpl implements WeatherPresenter {

    // todo fix
    private String apiKey = "fd31115891484d48d99956875d92b286";

    @Inject
    Retrofit retrofit;
    private Application app;
    private OpenWeatherApi api;
    private WeatherView view;

    public WeatherPresenterImpl(Application app) {
        this.app = app;
        ((App) app).getNetComponent().inject(this);
        api = retrofit.create(OpenWeatherApi.class);
    }


    @Override
    public void setView(WeatherView view) {
        this.view = view;
    }

    @Override
    public void loadWeather(String city) {
        api.getWeatherByCityName(city,apiKey).enqueue(new Callback<OpenWeather>() {
            @Override
            public void onResponse(Call<OpenWeather> call, Response<OpenWeather> response) {
                setWeatherData(response.body());
            }

            @Override
            public void onFailure(Call<OpenWeather> call, Throwable t) {

            }
        });
    }

    private void setWeatherData(OpenWeather weatherData){
        WeatherForecast currentWeather = weatherData.getWeatherForecastList().get(0);
    }
}
