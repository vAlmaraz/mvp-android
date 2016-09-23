package com.valmaraz.mvp.presenter;

import com.valmaraz.mvp.model.entity.City;
import com.valmaraz.mvp.model.weather.WeatherRepository;
import com.valmaraz.mvp.view.WeatherDetailsView;

/**
 * Created by Victor on 08/05/2016.
 * http://www.valmaraz.com
 */
public class WeatherDetailsPresenter implements Presenter, WeatherRepository.WeatherDetailsListener {

    private WeatherDetailsView view;
    private WeatherRepository weatherRepository;
    private City city;

    public WeatherDetailsPresenter(WeatherDetailsView view, City city) {
        this.view = view;
        this.city = city;
        weatherRepository = new WeatherRepository(view.getContext());
    }

    // =============================================================================
    // PRESENTER
    // =============================================================================

    @Override
    public void initialize() {
        view.renderInitialData(city);
        weatherRepository.getDetails(city, this);
    }

    @Override
    public void destroy() {
        view = null;
        city = null;
        weatherRepository = null;
    }

    // =============================================================================
    // MODEL CALLBACK
    // =============================================================================

    @Override
    public void onWeatherDetailsReceived(City city) {
        view.renderCityDetail(city);
    }

    @Override
    public void onFailure(String message) {
        view.showTemporaryMessage(message);
    }
}
