package com.example.mzglinicki96.mojtest.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mzglinicki96.mojtest.R;
import com.example.mzglinicki96.mojtest.webSevice.Weather;
import com.example.mzglinicki96.mojtest.webSevice.WeatherData;
import com.example.mzglinicki96.mojtest.webSevice.WeatherService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mzglinicki.96 on 11.04.2016.
 */
public class WeatherFragment extends ParentFragment {

    private static final int MAX_SUNNY_CLOUDS = 20;
    private static final int MAX_MOSTLY_CLOUDS = 60;
    private static final int TIME_MULTIPLIER = 1000;
    private static final double KELVIN = 273.15;
    private static final String CITY = "city";
    private static final String BASE_URL = "http://api.openweathermap.org";
    private static final String APP_ID = "c6978604313af7e104314fe497f8264c";
    private static final String PERCENTAGE = "%";
    private static final String WIND_UNIT = " m/s";
    private static final String PRESSURE_UNIT = " hPa";

    public WeatherFragment() {
        layoutId = R.layout.fragment_weather;
    }

    @Override
    protected void init(View view) {

        Bundle extras = getArguments();
        String city = extras.getString(CITY);

        TextView cityName = (TextView) view.findViewById(R.id.city_name_weather);
        cityName.setText(city);
        createRetrofit(city);
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

        WeatherData data = response.body();

        int clouds = data.clouds.all;

        setImageView(clouds);

        if (getView() == null) {
            return;
        }

        List<Weather> weather = data.weather;
        if (!weather.isEmpty()){
            TextView weatherCondition = (TextView) getView().findViewById(R.id.weather_condition_value);
            weatherCondition.setText(weather.get(0).description);
        }

        TextView sunRiseValue = (TextView) getView().findViewById(R.id.sunrise_value);
        sunRiseValue.setText(convertSunTime(data.sys.sunrise));

        TextView tempValue = (TextView) getView().findViewById(R.id.temp_value);
        String tempFormat = getContext().getString(R.string.degrees_value_format);
        tempValue.setText(String.format(tempFormat, convertToCelsius(data.main.temp)));

        TextView humidityValue = (TextView) getView().findViewById(R.id.humidity_value);
        humidityValue.setText("" + data.main.humidity + PERCENTAGE);

        TextView cloudsValue = (TextView) getView().findViewById(R.id.clouds_value);
        cloudsValue.setText("" + clouds + PERCENTAGE);

        TextView windValue = (TextView) getView().findViewById(R.id.wind_value);
        windValue.setText("" + data.wind.speed + WIND_UNIT);

        TextView pressureValue = (TextView) getView().findViewById(R.id.pressure_value);
        pressureValue.setText("" + data.main.pressure + PRESSURE_UNIT);
    }

    private String convertSunTime(int unixTime) {
        unixTime = unixTime * TIME_MULTIPLIER;
        Date date = new Date(unixTime);
        return new SimpleDateFormat("hh:mm").format(date);
    }

    double convertToCelsius(double temp) {
        return temp - KELVIN;
    }

    public void setImageView(int clouds) {

        if (getView() == null) {
            return;
        }
        RelativeLayout iconLayout = (RelativeLayout) getView().findViewById(R.id.weather_icon_layout);
        int weatherIconId;

        if (clouds <= MAX_SUNNY_CLOUDS) {
            weatherIconId = R.drawable.sunny;
        } else if (clouds > MAX_SUNNY_CLOUDS && clouds <= MAX_MOSTLY_CLOUDS) {
            weatherIconId = R.drawable.mostly_cloudy;
        } else {
            weatherIconId = R.drawable.cloudy;
        }
        ImageView newImage = new ImageView(getContext());
        newImage.setImageResource(weatherIconId);
        iconLayout.addView(newImage);
    }
}
