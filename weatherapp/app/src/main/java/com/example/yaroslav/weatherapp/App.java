package com.example.yaroslav.weatherapp;


import android.app.Application;

import com.example.yaroslav.weatherapp.dagger.AppModule;
import com.example.yaroslav.weatherapp.dagger.DaggerNetComponent;
import com.example.yaroslav.weatherapp.dagger.NetComponent;
import com.example.yaroslav.weatherapp.dagger.NetModule;
import com.example.yaroslav.weatherapp.dagger.PresenterModule;

public class App extends Application{

    private NetComponent netComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://api.openweathermap.org/data/2.5/"))
                .presenterModule(new PresenterModule())
                .build();

    }

    public NetComponent getNetComponent() {
        return netComponent;
    }

}
