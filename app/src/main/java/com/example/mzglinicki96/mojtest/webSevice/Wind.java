
package com.example.mzglinicki96.mojtest.webSevice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName(WebConstants.SPEED)
    @Expose
    public double speed;
    @SerializedName(WebConstants.DEG)
    @Expose
    public double deg;

}
