
package com.example.mzglinicki96.mojtest.webSevice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName(WebConstants.COUNTRY)
    @Expose
    public String country;
    @SerializedName(WebConstants.SUNRISE)
    @Expose
    public int sunrise;
    @SerializedName(WebConstants.SUNSET)
    @Expose
    public int sunset;

}
