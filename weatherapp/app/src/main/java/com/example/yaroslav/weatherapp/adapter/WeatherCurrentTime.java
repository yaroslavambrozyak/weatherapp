package com.example.yaroslav.weatherapp.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yaroslav.weatherapp.R;
import com.example.yaroslav.weatherapp.model.WeatherForecast;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WeatherCurrentTime extends RecyclerView.Adapter<WeatherCurrentTime.ViewHolder> {

    private List<WeatherForecast> data;

    public WeatherCurrentTime(List<WeatherForecast> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_card_current,parent,false);
        return new ViewHolder(view,parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView weatherIcon;
        TextView textTemperature;
        TextView time;

        Context context;

        public ViewHolder(View itemView,Context context) {
            super(itemView);
            weatherIcon = (ImageView) itemView.findViewById(R.id.weather_card_current_icon);
            textTemperature = (TextView) itemView.findViewById(R.id.weather_car_current_temperature);
            time = (TextView) itemView.findViewById(R.id.weather_card_current_time);
            this.context = context;
        }

        void bind(WeatherForecast weatherForecast){
            String iconUrl = "http://openweathermap.org/img/w/" + weatherForecast.getWeather().get(0).getIcon() + ".png";
            Picasso.with(context).load(iconUrl).into(weatherIcon);
            textTemperature.setText(String.valueOf(weatherForecast.getMain().getTemp()));
            time.setText(weatherForecast.getDate().substring(11));
        }
    }
}
