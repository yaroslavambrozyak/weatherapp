package com.example.yaroslav.weatherapp.presenter;


import android.app.Application;
import android.util.Log;

import com.example.yaroslav.weatherapp.App;
import com.example.yaroslav.weatherapp.Constants;
import com.example.yaroslav.weatherapp.api.OpenWeatherApi;
import com.example.yaroslav.weatherapp.model.OpenWeather;
import com.example.yaroslav.weatherapp.model.WeatherForecast;
import com.example.yaroslav.weatherapp.view.WeatherView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherPresenterImpl implements WeatherPresenter {

    @Inject
    Retrofit retrofit;
    private Application app;
    private OpenWeatherApi api;
    private WeatherView view;
    private String currentTime;
    private String currentDate;

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
        api.getWeatherByCityName(city, Constants.API_KEY, "metric").enqueue(new Callback<OpenWeather>() {
            @Override
            public void onResponse(Call<OpenWeather> call, Response<OpenWeather> response) {
                setCurrentWeatherData(response.body());
                setDayWeather(response.body());
                setTimeWeather(response.body());
            }

            @Override
            public void onFailure(Call<OpenWeather> call, Throwable t) {
                Log.d("TAG", "err");
            }
        });
    }

    private void setCurrentWeatherData(OpenWeather weatherData) {
        WeatherForecast currentWeather = weatherData.getWeatherForecastList().get(0);
        view.setCurrentTemperature(String.valueOf(currentWeather.getMain().getTemp()));
        view.setCurrentTemperatureMin(String.valueOf(currentWeather.getMain().getTempMin()));
        view.setCurrentTemperatureMax(String.valueOf(currentWeather.getMain().getTempMax()));
        view.setCurrentDescription(currentWeather.getWeather().get(0).getDescription());
        view.setCurrentWeatherIcon(currentWeather.getWeather().get(0).getIcon());
        view.setCurrentClouds(String.valueOf(currentWeather.getClouds().getAll()));
        view.setCurrentWindSpeed(String.valueOf(currentWeather.getWind().getSpeed()));
        view.setCurrentRain(String.valueOf(currentWeather.getRain().get_3h()));
        view.setCurrentData(currentWeather.getDate());
        currentTime = currentWeather.getDate().substring(11);
        currentDate = currentWeather.getDate().substring(0, 11);
    }

    private void setDayWeather(OpenWeather openWeather) {
        view.setDayWeatherData(findNextDayWeather(openWeather.getWeatherForecastList()));
    }

    private void setTimeWeather(OpenWeather openWeather) {
        view.setTimeWeatherData(findTimeWeather(openWeather.getWeatherForecastList()));
    }

    private List<WeatherForecast> findNextDayWeather(List<WeatherForecast> openWeather) {
        List<WeatherForecast> data = new ArrayList<>(5);
        for (WeatherForecast weather : openWeather) {
            if (weather.getDate().substring(11).equals(currentTime))
                data.add(weather);
        }
        return data;
    }

    private List<WeatherForecast> findTimeWeather(List<WeatherForecast> openWeather) {
        List<WeatherForecast> data = new ArrayList<>();
        for (WeatherForecast weather : openWeather) {
            if (weather.getDate().substring(0, 11).equals(currentDate))
                data.add(weather);
        }
        return data;
    }


}
