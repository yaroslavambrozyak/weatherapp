package com.example.yaroslav.weatherapp.api;


import com.example.yaroslav.weatherapp.model.OpenWeather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OpenWeatherApi {

    @GET("forecast")
    Call<OpenWeather> getWeatherByCityId(@Query("id") int id, @Query("appid") String appid);

}
