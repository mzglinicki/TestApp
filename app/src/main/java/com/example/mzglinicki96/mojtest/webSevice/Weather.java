
package com.example.mzglinicki96.mojtest.webSevice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName(WebConstants.ID)
    @Expose
    public int id;
    @SerializedName(WebConstants.MAIN)
    @Expose
    public String main;
    @SerializedName(WebConstants.DESCRIPTION)
    @Expose
    public String description;
    @SerializedName(WebConstants.ICON)
    @Expose
    public String icon;

}
