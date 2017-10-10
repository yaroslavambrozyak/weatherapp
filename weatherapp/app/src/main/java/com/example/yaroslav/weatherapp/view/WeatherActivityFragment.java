package com.example.yaroslav.weatherapp.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yaroslav.weatherapp.App;
import com.example.yaroslav.weatherapp.Constants;
import com.example.yaroslav.weatherapp.R;
import com.example.yaroslav.weatherapp.presenter.WeatherPresenter;

import javax.inject.Inject;


public class WeatherActivityFragment extends Fragment implements WeatherView {

    @Inject
    WeatherPresenter presenter;

    private String city;

    public WeatherActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            city = (String) savedInstanceState.get(Constants.CITY_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((App) getActivity().getApplication()).getNetComponent().inject(this);
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        presenter.setView(this);
        presenter.loadWeather(city);
        return view;
    }
}
