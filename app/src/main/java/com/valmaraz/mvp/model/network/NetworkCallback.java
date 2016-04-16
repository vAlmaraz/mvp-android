package com.valmaraz.mvp.model.network;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public interface NetworkCallback {
    void success(String body);
    void failure(String body);
}
