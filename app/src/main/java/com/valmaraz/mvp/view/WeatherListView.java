package com.valmaraz.mvp.view;

import com.valmaraz.mvp.model.entity.City;
import com.valmaraz.mvp.model.entity.Weather;

import java.util.List;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public interface WeatherListView extends LoadingView {
    void renderWeatherList(List<City> weatherCities);
}
