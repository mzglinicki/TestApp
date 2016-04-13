
package com.example.mzglinicki96.mojtest.webSevice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName(WebConstants.TEMP)
    @Expose
    public double temp;
    @SerializedName(WebConstants.HUMIDITY)
    @Expose
    public int humidity;
    @SerializedName(WebConstants.PRESSURE)
    @Expose
    public int pressure;
    @SerializedName(WebConstants.TEMP_MIN)
    @Expose
    public double tempMin;
    @SerializedName(WebConstants.TEMP_MAX)
    @Expose
    public double tempMax;

}
