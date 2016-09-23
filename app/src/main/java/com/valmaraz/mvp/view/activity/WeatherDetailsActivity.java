package com.valmaraz.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.valmaraz.mvp.R;
import com.valmaraz.mvp.model.entity.City;
import com.valmaraz.mvp.model.weather.WeatherRepository;
import com.valmaraz.mvp.presenter.WeatherDetailsPresenter;
import com.valmaraz.mvp.view.WeatherDetailsView;
import com.valmaraz.mvp.view.component.ImageLoader;
import com.valmaraz.mvp.view.component.UIMessage;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Victor on 08/05/2016.
 * http://www.valmaraz.com
 */
public class WeatherDetailsActivity extends BaseActivity implements WeatherDetailsView {

    private static final String EXTRA_CITY = "EXTRA_CITY";

    @Bind(R.id.act_weatherdetails_container)
    View container;
    @Bind(R.id.act_weatherdetails_iv_weather)
    ImageView ivWeather;
    @Bind(R.id.act_weatherdetails_tv_temp)
    TextView tvTemp;
    @Bind(R.id.act_weatherdetails_tv_sky)
    TextView tvSky;
    @Bind(R.id.act_weatherdetails_container_temps)
    View containerTemps;
    @Bind(R.id.act_weatherdetails_tv_temp_max)
    TextView tvTempMax;
    @Bind(R.id.act_weatherdetails_tv_temp_min)
    TextView tvTempMin;
    @Bind(R.id.act_weatherdetails_tv_pressure)
    TextView tvPressure;
    @Bind(R.id.act_weatherdetails_tv_humidity)
    TextView tvHumidity;
    @Bind(R.id.act_weatherdetails_tv_wind)
    TextView tvWind;

    private WeatherDetailsPresenter weatherDetailsPresenter;

    public static Intent getCallingIntent(BaseActivity from, City city) {
        Intent intent = new Intent(from, WeatherDetailsActivity.class);
        intent.putExtra(EXTRA_CITY, new Gson().toJson(city));
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherdetails);
        ButterKnife.bind(this);

        City city = new Gson().fromJson(getIntent().getStringExtra(EXTRA_CITY), City.class);
        weatherDetailsPresenter = new WeatherDetailsPresenter(this, city);
        weatherDetailsPresenter.initialize();
    }

    // =============================================================================
    // VIEW IMPLEMENTATION
    // =============================================================================

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void showLoading() {
        // TODO: implement this
    }

    @Override
    public void hideLoading() {
        // TODO: implement this
    }

    @Override
    public void showRetry() {
        // TODO: implement this
    }

    @Override
    public void hideRetry() {
        // TODO: implement this
    }

    @Override
    public void showTemporaryMessage(String message) {
        UIMessage.showMessage(container, message);
    }

    @Override
    public void renderInitialData(City city) {
        setTitle(city.name);
        showBasicData(city);
    }

    @Override
    public void renderCityDetail(City city) {
        showCompleteData(city);
    }

    // =============================================================================
    // PRIVATE METHODS
    // =============================================================================

    private void showBasicData(City city) {
        String image = "";
        if (!city.weatherList.isEmpty()) {
            image = WeatherRepository.getIconUrlForId(city.weatherList.get(0).icon);
        }
        ImageLoader.loadImage(this, image, ivWeather);
        tvTemp.setText(getString(R.string.temperature_value, city.main.temp));
    }

    private void showCompleteData(City city) {
        showBasicData(city);
        tvSky.setText(city.weatherList.get(0).main);
        containerTemps.setVisibility(View.VISIBLE);
        tvTempMax.setText(getString(R.string.temperature_value, city.main.temp_max));
        tvTempMin.setText(getString(R.string.temperature_value, city.main.temp_min));
        tvPressure.setText(getString(R.string.pressure_value, city.main.pressure));
        tvHumidity.setText(getString(R.string.humidity_value, city.main.humidity));
        tvWind.setText(getString(R.string.wind_value, city.wind.speed));
    }
}
