package com.valmaraz.mvp.model.repository;

import com.valmaraz.mvp.Config;
import com.valmaraz.mvp.model.network.Network;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public class Client {

    protected Network network;

    public Client() {
        network = new Network(Config.API_URL);
    }

    protected String appendDefaultQueryString(String url) {
        return url + "&appid=" + Config.API_KEY + "&units=metric";
    }
}
