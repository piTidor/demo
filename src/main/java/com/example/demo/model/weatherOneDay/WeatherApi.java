package com.example.demo.model.weatherOneDay;

import lombok.Data;

@Data
public class WeatherApi {
    Cord coord;
    Weather[] weather;
    String base;
    MainWeather main;
    String visibility;
    Wind wind;
    Rain rain;
    Clouds clouds;
    String dt;
    Sys sys;
    String timezone;
    String id;
    String name;
    String cod;
}
