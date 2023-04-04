package com.example.demo.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherParser {
    private double temperature;
    private String description;
    private String city;
    private String country;

    public  WeatherParser(String latitude, String longitude) throws IOException, JSONException {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat="
                + latitude + "&lon=" + longitude + "&appid=095a5d30d59ab4044ed09af32d5a79a4&lang=ru&units=metric";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject jsonObject = new JSONObject(response.toString());
        JSONObject main = jsonObject.getJSONObject("main");
        temperature = main.getDouble("temp");
        description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
        city = jsonObject.getString("name");
        country = jsonObject.getJSONObject("sys").getString("country");
    }

    public double getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}