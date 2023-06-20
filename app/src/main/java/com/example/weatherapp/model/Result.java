package com.example.weatherapp.model;

import com.example.weatherapp.model.Clouds;
import com.example.weatherapp.model.Coord;
import com.example.weatherapp.model.Main;
import com.example.weatherapp.model.Sys;
import com.example.weatherapp.model.WeatherItem;
import com.example.weatherapp.model.Wind;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    private int visibility;
    private int timezone;
    private Main main;
    private Clouds clouds;
    private Sys sys;
    private int dt;
    private Coord coord;
    private List<WeatherItem> weather;
    private String name;
    private int cod;
    private int id;
    private String base;
    private Wind wind;

    public int getVisibility(){
        return visibility;
    }

    public int getTimezone(){
        return timezone;
    }

    public Main getMain(){
        return main;
    }

    public Clouds getClouds(){
        return clouds;
    }

    public Sys getSys(){
        return sys;
    }

    public int getDt(){
        return dt;
    }

    public Coord getCoord(){
        return coord;
    }

    public List<WeatherItem> getWeather(){
        return weather;
    }

    public String getName(){
        return name;
    }

    public int getCod(){
        return cod;
    }

    public int getId(){
        return id;
    }

    public String getBase(){
        return base;
    }

    public Wind getWind(){
        return wind;
    }
}