package com.valmaraz.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public class Main {

    @SerializedName("temp")
    public double temp;

    @SerializedName("pressure")
    public double pressure;

    @SerializedName("humidity")
    public double humidity;

    @SerializedName("temp_min")
    public double temp_min;

    @SerializedName("temp_max")
    public double temp_max;
}
