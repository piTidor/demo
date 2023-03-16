package com.example.demo.model.weatherOneDay;

import lombok.Data;

@Data
public class MainWeather {
    String temp;
    String feels_like;
    String temp_min;
    String temp_max;
    String pressure;
    String humidity;
    String sea_level;
    String grnd_level;
}
