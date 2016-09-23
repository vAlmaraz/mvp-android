package com.valmaraz.mvp.model.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.valmaraz.mvp.model.entity.City;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Victor on 14/05/2016.
 * http://www.valmaraz.com
 */
public class WeatherDict {

    private static final String FILE_NAME = "weather";

    private static final String KEY_LIST_DATA = "list_data";
    private static final String KEY_LIST_SAVED_TIME = "list_time";
    private static final String KEY_DETAILS = "details_data";
    private static final String KEY_DETAILS_SAVED_TIME = "details_time";

    // Saved data expires in five minutes
    private static final long LIFE_TIME_IN_MILLIS = 5 * 60 * 1000;

    private SharedPreferences sharedPreferences;

    public WeatherDict(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    // =============================================================================
    // LIST METHODS
    // =============================================================================

    public void saveList(List<City> cities) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_LIST_DATA, new Gson().toJson(cities));
        // Save current time to check if saved data is valid or obsolete
        editor.putLong(KEY_LIST_SAVED_TIME, getCurrentTimeInMillis());
        editor.apply();
    }

    public boolean savedListIsValid() {
        return checkIfDataIsValid(KEY_LIST_SAVED_TIME);
    }

    /**
     * Gets saved list of cities.
     *
     * @return The cities list or null if there is no data.
     */
    public List<City> getList() {
        List<City> cities = null;
        String citiesJson = sharedPreferences.getString(KEY_LIST_DATA, "");
        if (!TextUtils.isEmpty(citiesJson)) {
            cities = new Gson().fromJson(citiesJson, new TypeToken<List<City>>() {
            }.getType());
        }
        return cities;
    }

    // =============================================================================
    // DETAILS METHODS
    // =============================================================================

    public void saveDetails(City city) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Concat key and city id
        editor.putString(KEY_DETAILS + city.id, new Gson().toJson(city));
        // Save current time to check if saved data is valid or obsolete
        editor.putLong(KEY_DETAILS_SAVED_TIME + city.id, getCurrentTimeInMillis());
        editor.apply();
    }

    public boolean savedDetailsIsValid(int cityId) {
        return checkIfDataIsValid(KEY_DETAILS_SAVED_TIME + cityId);
    }

    /**
     * Gets the details data for city ID.
     *
     * @param cityId The city ID.
     * @return The city object or null if there is no data.
     */
    public City getDetails(int cityId) {
        City city = null;
        String cityJson = sharedPreferences.getString(KEY_DETAILS + cityId, "");
        if (!TextUtils.isEmpty(cityJson)) {
            city = new Gson().fromJson(cityJson, City.class);
        }
        return city;
    }

    // =============================================================================
    // PRIVATE METHODS
    // =============================================================================

    /**
     * Retrieves current time (UTC) in millis.
     *
     * @return The current time in millis.
     */
    private long getCurrentTimeInMillis() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        return calendar.getTimeInMillis();
    }

    /**
     * Saved data is valid if there is a saved time (time > 0)
     * and data was saved less than LIFE_TIME_IN_MILLIS ago.
     *
     * @param key The key used to save time when data was stored in preferences.
     * @return True if saved data is still valid. False, otherwise.
     */
    private boolean checkIfDataIsValid(String key) {
        long time = sharedPreferences.getLong(key, 0);
        // Saved data is valid if there is a saved time (time > 0)
        // and data was saved less than LIFE_TIME_IN_MILLIS ago
        return time > 0 && getCurrentTimeInMillis() - time < LIFE_TIME_IN_MILLIS;
    }
}
