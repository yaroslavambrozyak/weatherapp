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
    private OpenWeather openWeather;

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
                openWeather = response.body();
                setCurrentWeatherData(openWeather.getWeatherForecastList().get(0));
                setDayWeather(openWeather);
                setTimeWeather(openWeather);
            }

            @Override
            public void onFailure(Call<OpenWeather> call, Throwable t) {
                Log.d("TAG", "err");
            }
        });
    }

    @Override
    public void loadWeather(double lon, double lat) {
        api.getWeatherByLonAndLat(lat, lon, Constants.API_KEY, "metric").enqueue(new Callback<OpenWeather>() {
            @Override
            public void onResponse(Call<OpenWeather> call, Response<OpenWeather> response) {
                openWeather = response.body();
                setCurrentWeatherData(openWeather.getWeatherForecastList().get(0));
                setDayWeather(openWeather);
                setTimeWeather(openWeather);
            }

            @Override
            public void onFailure(Call<OpenWeather> call, Throwable t) {
                Log.d("TAG", "err");
            }
        });
    }

    @Override
    public void changeWeather(WeatherForecast weatherForecast) {
        setCurrentWeatherData(weatherForecast);
    }

    @Override
    public void changeDayWeather(WeatherForecast weatherForecast) {
        setCurrentWeatherData(weatherForecast);
        currentDate = weatherForecast.getDate().substring(0, 11);
        setTimeWeather(openWeather);
    }

    private void setCurrentWeatherData(WeatherForecast currentWeather) {
        view.setCurrentCountry(openWeather.getCity().getName() + ", " + openWeather.getCity().getCountry());
        view.setCurrentTemperature(String.valueOf(currentWeather.getMain().getTemp()) + Constants.CELSIUS);
        view.setCurrentTemperatureMin(String.valueOf(currentWeather.getMain().getTempMin()) + Constants.CELSIUS);
        view.setCurrentTemperatureMax(String.valueOf(currentWeather.getMain().getTempMax()) + Constants.CELSIUS);
        view.setCurrentDescription(currentWeather.getWeather().get(0).getDescription());
        view.setCurrentWeatherIcon(currentWeather.getWeather().get(0).getIcon());
        view.setCurrentClouds(String.valueOf(currentWeather.getClouds().getAll()) + Constants.PERCENT);
        view.setCurrentWindSpeed(currentWeather.getWind().getSpeed() + Constants.METER_PER_SEC);
        if (currentWeather.getRain() != null)
            view.setCurrentRain(currentWeather.getRain().get_3h() + Constants.MIL);
        else if (currentWeather.getSnow() != null)
            view.setCurrentRain(currentWeather.getSnow().get_3h() + Constants.MIL);
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
