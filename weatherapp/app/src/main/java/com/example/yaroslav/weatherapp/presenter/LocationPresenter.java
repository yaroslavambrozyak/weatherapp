package com.example.yaroslav.weatherapp.presenter;


import com.example.yaroslav.weatherapp.view.LocationView;

public interface LocationPresenter {

    void setView(LocationView view);
    void searchByCurrentLocation(double lon, double lan);
    void searchByCityName(String cityName);

}
