package com.example.mzglinicki96.mojtest.webSevice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mzglinicki.96 on 11.04.2016.
 */
public interface WeatherService {

    @GET(WebConstants.REQUEST)
    Call<WeatherData> getWeatherData(@Query(WebConstants.QUERY_CITY_NAME) String cityName, @Query(WebConstants.QUERY_APPID) String appid);
}
