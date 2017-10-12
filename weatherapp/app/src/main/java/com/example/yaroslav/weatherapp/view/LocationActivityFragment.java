package com.example.yaroslav.weatherapp.view;

import android.Manifest;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
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
import android.widget.Toast;

import com.example.yaroslav.weatherapp.App;
import com.example.yaroslav.weatherapp.Constants;
import com.example.yaroslav.weatherapp.R;
import com.example.yaroslav.weatherapp.model.Weather;
import com.example.yaroslav.weatherapp.presenter.LocationPresenter;
import com.example.yaroslav.weatherapp.utils.LocationFinder;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.LOCATION_SERVICE;


public class LocationActivityFragment extends Fragment implements LocationView {

    @Inject
    LocationPresenter presenter;
    @Inject
    Application app;
    @BindView(R.id.edit_text_city)
    AutoCompleteTextView textView;
    private ProgressDialog progressDialog;
    LocationManager locationManager;
    LocationListener listener;


    public LocationActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((App) getActivity().getApplication()).getNetComponent().inject(this);
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        presenter.setView(this);
        initProgress();
        initLocation();
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.button_search_by_city)
    public void onButtonLocationByCityClick() {
        String cityName = textView.getText().toString();
        presenter.searchByCityName(cityName);
    }

    @OnClick(R.id.button_search_by_current)
    public void onButtonLocationByCurrentClick() {
        progressDialog.show();
        getLocation();

    }

    @Override
    public void launchWeatherActivity(String city) {
        Intent intent = new Intent(getActivity(), WeatherActivity.class);
        intent.putExtra(Constants.BY_CITY, true);
        intent.putExtra(Constants.CITY_NAME, city);
        startActivity(intent);
    }

    @Override
    public void launchWeatherActivity(double lon, double lat) {
        Intent intent = new Intent(getActivity(), WeatherActivity.class);
        intent.putExtra(Constants.BY_CITY, false);
        intent.putExtra(Constants.LONGITUDE, lon);
        intent.putExtra(Constants.LATITUDE, lat);
        startActivity(intent);
    }


    private void getLocation() {
        getLoc();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                getLoc();
                break;
            default:
                break;
        }
    }


    private void getLoc() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, listener);

    }

    private void initProgress() {
        progressDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Get location...");

    }

    private void initLocation(){
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("TAG", location.getLongitude() + " " + location.getLatitude());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    presenter.searchByCurrentLocation(location.getLongitude(), location.getLatitude());
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };
    }
}
