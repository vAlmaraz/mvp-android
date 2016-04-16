package com.valmaraz.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public class City {

    @SerializedName("coord")
    public Coord coord;

    @SerializedName("weather")
    public List<Weather> weatherList;

    @SerializedName("base")
    public String base;

    @SerializedName("main")
    public Main main;

    @SerializedName("wind")
    public Wind wind;

    @SerializedName("clouds")
    public Clouds clouds;

    @SerializedName("dt")
    public int dt;

    @SerializedName("sys")
    public Sys sys;

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("cod")
    public int cod;
}
