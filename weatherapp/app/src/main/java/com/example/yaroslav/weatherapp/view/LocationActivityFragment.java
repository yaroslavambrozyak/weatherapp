package com.example.yaroslav.weatherapp.view;

import android.app.Application;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.yaroslav.weatherapp.App;
import com.example.yaroslav.weatherapp.R;
import com.example.yaroslav.weatherapp.presenter.LocationPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LocationActivityFragment extends Fragment implements LocationView {

    @Inject
    LocationPresenter presenter;
    @Inject
    Application app;
    @BindView(R.id.edit_text_city)
    EditText editTextCity;

    public LocationActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((App)getActivity().getApplication()).getNetComponent().inject(this);
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        presenter.setView(this);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.button_search_by_city)
    public void onButtonLocationByCityClick() {
        String city = editTextCity.getText().toString();
        if (!city.isEmpty())
            presenter.searchByCityName();
    }

    @OnClick(R.id.button_search_by_current)
    public void onButtonLocationByCurrentClick(){
        presenter.searchByCurrentLocation();
    }
}