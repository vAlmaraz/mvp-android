package com.valmaraz.mvp.presenter;

import com.valmaraz.mvp.model.entity.City;
import com.valmaraz.mvp.model.weather.WeatherRepository;
import com.valmaraz.mvp.view.WeatherListView;

import java.util.List;

/**
 * Created by Victor on 13/04/2016.
 * http://www.valmaraz.com
 */
public class WeatherListPresenter implements Presenter, WeatherRepository.WeatherListListener {

    private WeatherListView view;
    private WeatherRepository weatherRepository;

    public WeatherListPresenter(WeatherListView view) {
        this.view = view;
        this.weatherRepository = new WeatherRepository(view.getContext());
    }

    // =============================================================================
    // PRESENTER
    // =============================================================================

    @Override
    public void initialize() {
        view.showLoading();
        view.hideRetry();
        weatherRepository.getList(this);
    }

    @Override
    public void destroy() {
        view = null;
        weatherRepository = null;
    }

    // =============================================================================
    // MODEL CALLBACK
    // =============================================================================

    @Override
    public void onWeatherListReceived(List<City> cities) {
        view.hideLoading();
        view.renderWeatherList(cities);
    }

    @Override
    public void onFailure(String message) {
        view.hideLoading();
        view.showRetry();
        view.showTemporaryMessage(message);
    }
}
