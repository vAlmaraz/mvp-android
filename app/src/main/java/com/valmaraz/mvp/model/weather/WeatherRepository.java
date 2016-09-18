package com.valmaraz.mvp.model.weather;

import android.content.Context;
import android.support.annotation.NonNull;

import com.valmaraz.mvp.Environment;
import com.valmaraz.mvp.Log;
import com.valmaraz.mvp.model.RepositoryListener;
import com.valmaraz.mvp.model.entity.City;

import java.util.List;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public class WeatherRepository {

    private static final String TAG = WeatherRepository.class.getName();

    private WeatherClient client;
    private WeatherCache cache;

    public WeatherRepository(Context context) {
        client = new WeatherClient();
        cache = new WeatherCache(context);
    }

    public void getList(@NonNull final WeatherListListener callback) {
        Log.i(TAG, "Requesting list...");
        if (cache.savedListIsValid()) {
            Log.i(TAG, "Saved list is still valid");
            // Return saved data
            callback.onWeatherListReceived(cache.getList());
        } else {
            Log.i(TAG, "No saved list or obsolete. Requesting new data...");
            // Request data from client
            int idBarcelona = 3128760;
            int idMadrid = 3117735;
            int idSalamanca = 3111108;
            int idValencia = 2509954;
            int idValladolid = 3106672;
            int[] cityIds = new int[]{idBarcelona, idMadrid, idSalamanca, idValencia, idValladolid};

            WeatherListListener listener = new WeatherListListener() {
                @Override
                public void onWeatherListReceived(List<City> cities) {
                    Log.i(TAG, "Saving new list...");
                    // Save response for future requests
                    cache.saveList(cities);
                    // Return response to callback
                    callback.onWeatherListReceived(cities);
                }

                @Override
                public void onFailure(String message) {
                    callback.onFailure(message);
                }
            };

            client.getList(cityIds, listener);
        }
    }

    public void getDetails(@NonNull City city, @NonNull final WeatherDetailsListener callback) {
        Log.i(TAG, "Requesting details for " + city.name + " (" + city.id + ")...");
        if (cache.savedDetailsIsValid(city.id)) {
            Log.i(TAG, "Details are still valid for " + city.name + " (" + city.id + ")");
            // Return saved data
            callback.onWeatherDetailsReceived(cache.getDetails(city.id));
        } else {
            Log.i(TAG, "No saved details or obsolete. Requesting new data for " + city.name + " (" + city.id + ")...");
            // Request data from client
            WeatherDetailsListener listener = new WeatherDetailsListener() {
                @Override
                public void onWeatherDetailsReceived(City city) {
                    Log.i(TAG, "Saving new details for" + city.name + " (" + city.id + ")...");
                    // Save response for future requests
                    cache.saveDetails(city);
                    // Return response to callback
                    callback.onWeatherDetailsReceived(city);
                }

                @Override
                public void onFailure(String message) {
                    callback.onFailure(message);
                }
            };

            client.getDetails(city, listener);
        }
    }

    // TODO: find a better place for this method (business object)
    public static String getIconUrlForId(String iconId) {
        return Environment.apiUrl + "/img/w/" + iconId;
    }

    // =============================================================================
    // CALLBACKS
    // =============================================================================

    public interface WeatherListListener extends RepositoryListener {
        void onWeatherListReceived(List<City> cities);
    }

    public interface WeatherDetailsListener extends RepositoryListener {
        void onWeatherDetailsReceived(City city);
    }
}
