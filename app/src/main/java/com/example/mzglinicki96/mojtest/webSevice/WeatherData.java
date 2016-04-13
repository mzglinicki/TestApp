
package com.example.mzglinicki96.mojtest.webSevice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherData {

    @SerializedName(WebConstants.COORD)
    @Expose
    public Coord coord;
    @SerializedName(WebConstants.SYSTEM)
    @Expose
    public Sys sys;
    @SerializedName(WebConstants.WEATHER)
    @Expose
    public List<Weather> weather = new ArrayList<Weather>();
    @SerializedName(WebConstants.MAIN)
    @Expose
    public Main main;
    @SerializedName(WebConstants.WIND)
    @Expose
    public Wind wind;
    @SerializedName(WebConstants.RAIN)
    @Expose
    public Rain rain;
    @SerializedName(WebConstants.CLOUDS)
    @Expose
    public Clouds clouds;
    @SerializedName(WebConstants.DT)
    @Expose
    public int dt;
    @SerializedName(WebConstants.ID)
    @Expose
    public int id;
    @SerializedName(WebConstants.NAME)
    @Expose
    public String name;
    @SerializedName(WebConstants.COD)
    @Expose
    public int cod;

}
