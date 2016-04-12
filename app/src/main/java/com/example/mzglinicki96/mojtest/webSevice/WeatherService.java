package com.example.mzglinicki96.mojtest.webSevice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mzglinicki.96 on 11.04.2016.
 */
public interface WeatherService {

    @GET("data/2.5/weather")
    Call<WeatherData> getWeatherData(@Query("q") String cityName, @Query("APPID") String appid);
}
