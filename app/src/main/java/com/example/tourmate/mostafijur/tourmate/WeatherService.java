package com.example.tourmate.mostafijur.tourmate;

//import com.bitm.mad.locationmad44.currentweather.CurrentWeatherResponse;

import com.example.tourmate.mostafijur.tourmate.forecastweather.ForecastResponse;
import com.example.tourmate.mostafijur.tourmate.weather.CurrentWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WeatherService {
    @GET
    Call<CurrentWeatherResponse>getCurrentWeatherResponse(@Url String endUrl);
    @GET
    Call<ForecastResponse>getForecastResponse(@Url String endUrl);

}
