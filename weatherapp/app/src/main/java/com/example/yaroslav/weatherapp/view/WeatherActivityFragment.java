package com.example.yaroslav.weatherapp.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yaroslav.weatherapp.App;
import com.example.yaroslav.weatherapp.Constants;
import com.example.yaroslav.weatherapp.R;
import com.example.yaroslav.weatherapp.adapter.WeatherCurrentTime;
import com.example.yaroslav.weatherapp.adapter.WeatherDayAdapter;
import com.example.yaroslav.weatherapp.model.WeatherForecast;
import com.example.yaroslav.weatherapp.presenter.WeatherPresenter;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WeatherActivityFragment extends Fragment implements WeatherView {

    @Inject
    WeatherPresenter presenter;
    @BindView(R.id.recycler_weather)
    RecyclerView weatherDay;
    @BindView(R.id.weather_current_recycler)
    RecyclerView weatherTime;
    @BindView(R.id.weather_icon)
    ImageView weatherIcon;
    @BindView(R.id.text_view_description)
    TextView textDescription;
    @BindView(R.id.text_view_temperature)
    TextView textTemperature;
    @BindView(R.id.text_view_temperature_min)
    TextView textTemperatureMin;
    @BindView(R.id.text_view_temperature_max)
    TextView textTemperatureMax;
    @BindView(R.id.text_view_clouds)
    TextView textClouds;
    @BindView(R.id.text_view_wind_speed)
    TextView textWindSpeed;
    @BindView(R.id.text_view_rain_pros)
    TextView textRain;
    @BindView(R.id.text_view_data)
    TextView textData;

    private String city;
    private List<WeatherForecast> data;
    private List<WeatherForecast> datacur;

    public WeatherActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<>();
        datacur = new ArrayList<>();
        if (savedInstanceState != null) {
            city = (String) savedInstanceState.get(Constants.CITY_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((App) getActivity().getApplication()).getNetComponent().inject(this);
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this,view);
        presenter.setView(this);
        initRecycler();
        presenter.loadWeather("London");
        return view;
    }

    @Override
    public void setCurrentWeatherIcon(String icon) {
        String iconUrl = "http://openweathermap.org/img/w/" + icon + ".png";
        Picasso.with(getActivity()).load(iconUrl).into(weatherIcon);
    }

    @Override
    public void setCurrentDescription(String description) {
        textDescription.setText(description);
    }

    @Override
    public void setCurrentTemperature(String temperature) {
        textTemperature.setText(temperature);
    }

    @Override
    public void setCurrentTemperatureMin(String temperatureMin) {
        textTemperatureMin.setText(temperatureMin);
    }

    @Override
    public void setCurrentTemperatureMax(String temperatureMax) {
        textTemperatureMax.setText(temperatureMax);
    }

    @Override
    public void setCurrentClouds(String clouds) {
        textClouds.setText(clouds);
    }

    @Override
    public void setCurrentWindSpeed(String windSpeed) {
        textWindSpeed.setText(windSpeed);
    }

    @Override
    public void setCurrentRain(String rain) {
        textRain.setText(rain);
    }

    @Override
    public void setCurrentData(String data) {
        textData.setText(data);
    }

    @Override
    public void setDayWeatherData(List<WeatherForecast> data) {
        this.data.addAll(data);
        weatherDay.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void setTimeWeatherData(List<WeatherForecast> data) {
        datacur.addAll(data);
        weatherTime.getAdapter().notifyDataSetChanged();
    }

    private void initRecycler(){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        weatherDay.setLayoutManager(manager);
        RecyclerView.Adapter adapter = new WeatherDayAdapter(data);
        weatherDay.setAdapter(adapter);

        RecyclerView.LayoutManager hmanager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        weatherTime.setLayoutManager(hmanager);
        RecyclerView.Adapter hadapter = new WeatherCurrentTime(datacur);
        weatherTime.setAdapter(hadapter);
    }
}
