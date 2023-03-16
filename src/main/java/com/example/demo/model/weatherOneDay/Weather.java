package com.example.demo.model.weatherOneDay;

import lombok.Data;

@Data
public class Weather {
    String id;
    String main;
    String description;
    String icon;
}
