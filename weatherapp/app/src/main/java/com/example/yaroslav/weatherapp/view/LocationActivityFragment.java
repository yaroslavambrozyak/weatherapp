package com.example.yaroslav.weatherapp.view;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.yaroslav.weatherapp.App;
import com.example.yaroslav.weatherapp.R;
import com.example.yaroslav.weatherapp.presenter.LocationPresenter;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LocationActivityFragment extends Fragment implements LocationView{

    @Inject
    LocationPresenter presenter;
    @Inject
    Application app;
    @BindView(R.id.edit_text_city)
    AutoCompleteTextView textView;

    public LocationActivityFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((App) getActivity().getApplication()).getNetComponent().inject(this);
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        presenter.setView(this);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.button_search_by_city)
    public void onButtonLocationByCityClick() {
        /*String cityName = textView.getText().toString();
        presenter.searchByCityName(cityName);*/
        Intent intent = new Intent(app,WeatherActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_search_by_current)
    public void onButtonLocationByCurrentClick() {
        presenter.searchByCurrentLocation();
    }


}
