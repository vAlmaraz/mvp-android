package com.valmaraz.mvp.model.repository;

import android.support.annotation.NonNull;

import com.valmaraz.mvp.model.entity.City;

import java.util.List;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public class WeatherRepository {

    private WeatherClient client;

    public WeatherRepository() {
        client = new WeatherClient();
    }

    public void getList(@NonNull WeatherListListener callback) {
        if (callback == null) {
            throw new RuntimeException("WeatherListListener cannot be null");
        }

        int idSalamanca = 3111108;
        int idMadrid = 3117735;
        int idValencia = 2509954;
        int idValladolid = 3106672;
        int idBarcelona = 3128760;
        int[] cityIds = new int[]{idSalamanca, idMadrid, idValencia, idValladolid, idBarcelona};
        client.getList(cityIds, callback);
    }

    public interface WeatherListListener extends RepositoryListener {
        void onWeatherListReceived(List<City> cities);
    }
}
