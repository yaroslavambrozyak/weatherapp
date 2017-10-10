package com.example.yaroslav.weatherapp.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OpenWeather {

    private int cod;
    private String message;
    private int cnt;
    @JsonProperty("list")
    private List<WeatherForecast> weatherForecastList;
    private City city;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<WeatherForecast> getWeatherForecastList() {
        return weatherForecastList;
    }

    public void setWeatherForecastList(List<WeatherForecast> weatherForecastList) {
        this.weatherForecastList = weatherForecastList;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
