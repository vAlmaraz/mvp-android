package com.valmaraz.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public class Sys {

    @SerializedName("type")
    public int type;

    @SerializedName("id")
    public int id;

    @SerializedName("message")
    public double message;

    @SerializedName("country")
    public String country;

    @SerializedName("sunrise")
    public int sunrise;

    @SerializedName("sunset")
    public int sunset;
}
