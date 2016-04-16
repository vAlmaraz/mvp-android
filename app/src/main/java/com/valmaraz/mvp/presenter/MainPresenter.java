package com.valmaraz.mvp.presenter;

import com.valmaraz.mvp.model.entity.City;
import com.valmaraz.mvp.model.repository.WeatherRepository;
import com.valmaraz.mvp.view.MainView;

import java.util.List;

/**
 * Created by Victor on 13/04/2016.
 * http://www.valmaraz.com
 */
public class MainPresenter implements Presenter, WeatherRepository.WeatherListListener {

    private MainView view;
    private WeatherRepository weatherRepository;

    public MainPresenter(MainView view) {
        this.view = view;
        this.weatherRepository = new WeatherRepository();
    }

    public void loadWeatherList() {
        weatherRepository.getList(this);
    }

    @Override
    public void onWeatherListReceived(List<City> cities) {
        view.renderWeatherList(cities);
    }

    @Override
    public void onFailure(String message) {
        view.showTemporaryMessage(message);
    }
}
