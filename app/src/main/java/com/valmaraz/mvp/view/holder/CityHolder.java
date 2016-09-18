package com.valmaraz.mvp.view.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.valmaraz.mvp.R;
import com.valmaraz.mvp.model.entity.City;
import com.valmaraz.mvp.model.weather.WeatherRepository;
import com.valmaraz.mvp.view.listener.OnHolderClickListener;
import com.valmaraz.mvp.view.component.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Victor on 13/04/2016.
 * http://www.valmaraz.com
 */
public class CityHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.item_city_iv_weather)
    ImageView ivWeather;
    @Bind(R.id.item_city_tv_city)
    TextView tvCity;
    @Bind(R.id.item_city_tv_temp)
    TextView tvTemp;

    private int holderPosition;
    private OnHolderClickListener clickListener;

    public static int getLayoutId() {
        return R.layout.item_city;
    }

    public CityHolder(View v, OnHolderClickListener clickListener) {
        super(v);
        this.clickListener = clickListener;
        ButterKnife.bind(this, v);
    }

    public void render(Context context, int position, City city) {
        holderPosition = position;
        // TODO: set default image or placeholder
        String image = "";
        if (!city.weatherList.isEmpty()) {
            image = WeatherRepository.getIconUrlForId(city.weatherList.get(0).icon);
        }
        ImageLoader.loadImage(context, image, ivWeather);
        tvCity.setText(city.name);
        tvTemp.setText(context.getString(R.string.temperature_value, city.main.temp));
    }

    @OnClick(R.id.item_main_container)
    public void onClick() {
        if (clickListener != null) {
            clickListener.onClick(holderPosition);
        }
    }
}
