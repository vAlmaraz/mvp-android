package com.valmaraz.mvp.model.repository;

import android.support.annotation.NonNull;

import com.valmaraz.mvp.Environment;
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
        int idBarcelona = 3128760;
        int idMadrid = 3117735;
        int idSalamanca = 3111108;
        int idValencia = 2509954;
        int idValladolid = 3106672;
        int[] cityIds = new int[]{idBarcelona, idMadrid, idSalamanca, idValencia, idValladolid};
        client.getList(cityIds, callback);
    }

    public void getDetails(@NonNull City city, @NonNull WeatherDetailsListener callback) {
        client.getDetails(city, callback);
    }

    // TODO: find a better place for this method (business object)
    public static String getIconUrlForId(String iconId) {
        return Environment.apiUrl + "/img/w/" + iconId;
    }

    public interface WeatherListListener extends RepositoryListener {
        void onWeatherListReceived(List<City> cities);
    }

    public interface WeatherDetailsListener extends RepositoryListener {
        void onWeatherDetailsReceived(City city);
    }
}
