package com.valmaraz.mvp.view;

import com.valmaraz.mvp.model.entity.City;

/**
 * Created by Victor on 08/05/2016.
 * http://www.valmaraz.com
 */
public interface WeatherDetailsView extends LoadingView {
    void renderInitialData(City city);
    void renderCityDetail(City city);
}
