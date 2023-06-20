package com.example.weatherapp.rest;

import com.example.weatherapp.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "https://api.openweathermap.org/data/2.5/";

//    api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=5e0b37c914a240dd68c841311795b913

    @GET("weather")
    Call<Result> getWeatherData(@Query("q") String name, @Query("APPID") String appid,@Query("units") String units);


}