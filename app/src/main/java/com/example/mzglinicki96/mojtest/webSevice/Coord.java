
package com.example.mzglinicki96.mojtest.webSevice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coord {

    @SerializedName(WebConstants.LOT)
    @Expose
    public float lon;
    @SerializedName(WebConstants.LAT)
    @Expose
    public float lat;

}
