
package com.example.mzglinicki96.mojtest.webSevice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rain {

    @SerializedName(WebConstants.TIM_FROM_RAIN)
    @Expose
    public int rainVolume;

}
