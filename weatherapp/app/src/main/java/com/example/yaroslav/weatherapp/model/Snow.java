package com.example.yaroslav.weatherapp.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Snow {

    @JsonProperty("3h")
    private double _3h;

    public double get_3h() {
        return _3h;
    }

    public void set_3h(double _3h) {
        this._3h = _3h;
    }
}
