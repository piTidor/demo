package com.example.demo.apiService;

import com.example.demo.model.Anekdote;
import com.example.demo.model.weatherOneDay.WeatherApi;
import org.springframework.web.client.RestTemplate;

public class FirstApi {


    public static String getAnekdot(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://official-joke-api.appspot.com/random_joke";
        Anekdote responce = restTemplate.getForObject(url, Anekdote.class);
//        System.out.println(responce);
        return  responce.getSetup() + "\n" + responce.getPunchline();
    }
    public static String getPogoda(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=56.8575&lon=60.6125&appid=095a5d30d59ab4044ed09af32d5a79a4&lang=ru&units=metric";
//        String url1 = "https://api.openweathermap.org/data/2.5/weather?q=Ekaterinburg&appid=095a5d30d59ab4044ed09af32d5a79a4";
        WeatherApi responce = restTemplate.getForObject(url, WeatherApi.class);
        String cel[] = responce.getMain().getTemp().split(".");
        System.out.println(String.valueOf(cel.length));
        

//        System.out.println(restTemplate.getForObject(url, String.class));
        String answer = "Сейчас " + responce.getMain().getTemp() + "°C" +"\n" + "За окном - " + responce.getWeather()[0].getDescription()
                + "\n" + "Скорость ветра " + responce.getWind().getSpeed() + " м/с";
        return answer;
    }
}
