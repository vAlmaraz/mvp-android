package com.valmaraz.mvp.model.repository;

import com.valmaraz.mvp.Environment;
import com.valmaraz.mvp.model.network.Network;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public class Client {

    protected Network network;

    public Client() {
        network = new Network(Environment.apiBaseUrl);
    }

    protected String appendDefaultQueryString(String url) {
        return url + "&appid=" + Environment.apiKey + "&units=metric";
    }
}
