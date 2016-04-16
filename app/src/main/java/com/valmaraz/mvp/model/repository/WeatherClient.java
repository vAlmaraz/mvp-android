package com.valmaraz.mvp.model.repository;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.valmaraz.mvp.model.entity.City;
import com.valmaraz.mvp.model.entity.Group;
import com.valmaraz.mvp.model.network.Network;
import com.valmaraz.mvp.model.network.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public class WeatherClient extends Client {

    public WeatherClient() {
        super();
    }

    public void getList(int[] cityIds, @NonNull final WeatherRepository.WeatherListListener callback) {
        // Build url
        StringBuilder sb = new StringBuilder();
        String delim = "";
        for (int cityId : cityIds) {
            sb.append(delim + cityId);
            delim = ",";
        }
        String url = "group?id=" + sb.toString();
        url = appendDefaultQueryString(url);

        // Configure response
        NetworkCallback networkCallback = new NetworkCallback() {
            @Override
            public void success(String body) {
                Group group = new Gson().fromJson(body, Group.class);
                callback.onWeatherListReceived(group.cities);
            }

            @Override
            public void failure(String body) {
                // TODO: set default error message
                String message = "";
                if (!TextUtils.isEmpty(body)) {
                    Error error = new Gson().fromJson(body, Error.class);
                    message = error.getMessage();
                }
                callback.onFailure(body);
            }
        };

        // Make request
        network.get(url, networkCallback);
    }
}
