package com.valmaraz.mvp.view.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.valmaraz.mvp.R;
import com.valmaraz.mvp.model.entity.City;
import com.valmaraz.mvp.model.repository.WeatherRepository;
import com.valmaraz.mvp.view.component.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Victor on 13/04/2016.
 * http://www.valmaraz.com
 */
public class MainHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.item_main_iv_weather)
    ImageView ivWeather;
    @Bind(R.id.item_main_tv_city)
    TextView tvCity;
    @Bind(R.id.item_main_tv_temp)
    TextView tvTemp;

    public MainHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public void render(Context context, City city) {
        // TODO: set default image or placeholder
        String image = "";
        if (!city.weatherList.isEmpty()) {
            image = WeatherRepository.getIconUrlForId(city.weatherList.get(0).icon);
        }
        ImageLoader.loadImage(context, image, ivWeather);
        tvCity.setText(city.name);
        tvTemp.setText(context.getString(R.string.temperature, city.main.temp));
    }
}
