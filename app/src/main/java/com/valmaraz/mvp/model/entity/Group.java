package com.valmaraz.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public class Group {

    @SerializedName("cnt")
    public int cnt;

    @SerializedName("list")
    public List<City> cities;
}
