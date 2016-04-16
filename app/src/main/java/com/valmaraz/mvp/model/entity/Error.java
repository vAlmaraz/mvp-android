package com.valmaraz.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public class Error {

    @SerializedName("cod")
    public int cod;

    @SerializedName("message")
    public String message;
}
