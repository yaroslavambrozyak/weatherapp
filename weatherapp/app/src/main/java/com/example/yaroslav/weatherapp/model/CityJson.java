package com.example.yaroslav.weatherapp.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class CityJson {

    private int id;
    private String name;
    private String country;
    @JsonProperty("coor")
    private Coordinates coordinates;

}
