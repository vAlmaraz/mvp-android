package com.valmaraz.mvp.model.weather;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.valmaraz.mvp.Environment;
import com.valmaraz.mvp.model.BaseCache;
import com.valmaraz.mvp.model.entity.CachedData;
import com.valmaraz.mvp.model.entity.City;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vAlmaraz on 18/09/2016.
 * https://www.valmaraz.com
 */

public class WeatherCache extends BaseCache {

    private static final String LIST_ID = "LIST";
    private static final String CACHE_WEATHER_FILE_NAME = "Weather.json";

    public WeatherCache(Context context) {
        super(context, CACHE_WEATHER_FILE_NAME);
    }

    // =============================================================================
    // LIST METHODS
    // =============================================================================

    public void saveList(List<City> cities) {
        String responseJson = gson.toJson(cities);
        // Search if there was a previous response
        CachedData cachedData = find(LIST_ID);
        // If there was no previous response, create object
        if (cachedData == null) {
            cachedData = new CachedData(LIST_ID);
        }
        // Update response
        cachedData.saveResponse(responseJson);
        save(cachedData);
    }

    public boolean savedListIsValid() {
        CachedData foundCachedData = find(LIST_ID);
        return foundCachedData != null && System.currentTimeMillis() - foundCachedData.savedTimestamp < Environment.CACHE_MAX_LIFETIME_IN_MILLIS;
    }

    /**
     * Gets saved list of cities.
     *
     * @return The cities list or null if there is no data.
     */
    public List<City> getList() {
        List<City> cities = null;

        CachedData foundCachedData = find(LIST_ID);
        if (foundCachedData != null && System.currentTimeMillis() - foundCachedData.savedTimestamp < Environment.CACHE_MAX_LIFETIME_IN_MILLIS) {
            Type listType = new TypeToken<ArrayList<City>>() {
            }.getType();
            cities = gson.fromJson(foundCachedData.responseJson, listType);
        }

        return cities;
    }

    // =============================================================================
    // DETAILS METHODS
    // =============================================================================

    public void saveDetails(City city) {
        String responseJson = gson.toJson(city);
        // Search if there was a previous response
        CachedData cachedData = find(getDetailsId(city.id));
        // If there was no previous response, create object
        if (cachedData == null) {
            cachedData = new CachedData(getDetailsId(city.id));
        }
        // Update response
        cachedData.saveResponse(responseJson);
        save(cachedData);
    }

    public boolean savedDetailsIsValid(int cityId) {
        CachedData foundCachedData = find(getDetailsId(cityId));
        return foundCachedData != null && System.currentTimeMillis() - foundCachedData.savedTimestamp < Environment.CACHE_MAX_LIFETIME_IN_MILLIS;
    }

    /**
     * Gets the details data for city ID.
     *
     * @param cityId The city ID.
     * @return The city object or null if there is no data.
     */
    public City getDetails(int cityId) {
        City city = null;

        CachedData foundCachedData = find(getDetailsId(cityId));
        if (foundCachedData != null && System.currentTimeMillis() - foundCachedData.savedTimestamp < Environment.CACHE_MAX_LIFETIME_IN_MILLIS) {
            city = gson.fromJson(foundCachedData.responseJson, City.class);
        }

        return city;
    }

    // =============================================================================
    // PRIVATE METHODS
    // =============================================================================

    private String getDetailsId(int cityId) {
        return "City_" + cityId;
    }
}
