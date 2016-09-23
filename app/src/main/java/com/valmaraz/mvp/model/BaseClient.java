package com.valmaraz.mvp.model;

import com.valmaraz.mvp.Environment;
import com.valmaraz.mvp.model.network.Network;
import com.valmaraz.mvp.model.network.NetworkCallback;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public abstract class BaseClient {

    private Network network;

    public BaseClient() {
        network = new Network(Environment.apiBaseUrl);
    }

    /**
     * Makes a get request appending default query string (api key) to url.
     *
     * @param url The url to request (without api key query string param)
     * @param callback The callback that should be called when finished.
     */
    protected void makeGetRequest(String url, NetworkCallback callback) {
        network.get(appendDefaultQueryString(url), callback);
    }

    /**
     * Appends the default query string params that should be in every requests,
     * like api key and units.
     *
     * @param url The url that should be concatenated with default params.
     * @return The complete url with all query string params.
     */
    private String appendDefaultQueryString(String url) {
        return url + "&appid=" + Environment.apiKey + "&units=metric";
    }
}
