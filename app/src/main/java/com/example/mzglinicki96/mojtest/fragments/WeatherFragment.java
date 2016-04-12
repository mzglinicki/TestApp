package com.example.mzglinicki96.mojtest.fragments;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mzglinicki96.mojtest.R;
import com.example.mzglinicki96.mojtest.webSevice.WeatherData;
import com.example.mzglinicki96.mojtest.webSevice.WeatherService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mzglinicki.96 on 11.04.2016.
 */
public class WeatherFragment extends ParentFragment {

    public WeatherFragment() {
        layoutId = R.layout.fragment_weather;
    }

    public static final String BASE_URL = "http://api.openweathermap.org";
    public static final String APP_ID = "c6978604313af7e104314fe497f8264c";
    public static final double KELVIN = 273.15;
    public static final int TIME_MULTIPLIER = 1000;
    public static final String CITY = "Lodz";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createRetrofit(CITY);
    }

    public void createRetrofit(String city) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        getService(service, city);
    }

    private void getService(WeatherService service, String city) {
        Call<WeatherData> call = service.getWeatherData(city, APP_ID);
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                writeWeatherInfo(response);
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
            }
        });
    }

    private void writeWeatherInfo(Response<WeatherData> response) {

        int sunRise = response.body().sys.sunrise;
        double temp = (response.body().main.temp);
        int humidity = response.body().main.humidity;
        int clouds = response.body().clouds.all;

        TextView dataTextView = (TextView) getView().findViewById(R.id.data_text_view);
        dataTextView.setText(getDate());


        TextView sunRiseValue = (TextView) getView().findViewById(R.id.sun_rise_value);
        sunRiseValue.setText(convertSunTime(sunRise));

        TextView tempValue = (TextView) getView().findViewById(R.id.temp_value);
        tempValue.setText("" + roundTwoDecimals(temp) + "°C");

        TextView humidityValue = (TextView) getView().findViewById(R.id.humidity_value);
        humidityValue.setText("" + humidity + "%");

        TextView cloudsValue = (TextView) getView().findViewById(R.id.clouds_value);
        cloudsValue.setText("" + clouds + "%");
    }

    private String convertSunTime(int unixTime) {
        unixTime = unixTime * TIME_MULTIPLIER;
        Date date = new Date(unixTime);
        return new SimpleDateFormat("hh:mm").format(date);
    }

    double roundTwoDecimals(double temp) {
        temp = temp - KELVIN;
        return Math.round(temp);
    }

    public String getDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy", Locale.getDefault());
        return formatter.format(calendar.getTime());
    }
}
