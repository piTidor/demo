package com.example.demo.apiService;

import com.binance.connector.client.impl.SpotClientImpl;
import com.example.demo.model.Anekdote;
import com.example.demo.model.binancewalet.BinanceSnapshot;
import com.example.demo.model.binancewalet.BinanceVal;
import com.example.demo.model.weatherOneDay.WeatherApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

public class FirstApi {


    public static String getAnekdot(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://official-joke-api.appspot.com/random_joke";
        Anekdote responce = restTemplate.getForObject(url, Anekdote.class);
//        System.out.println(responce);
        return  responce.getSetup() + "\n" + responce.getPunchline();
    }
    public static String getPogoda(Long id){
        String answer;

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=56.8575&lon=60.6125&appid=095a5d30d59ab4044ed09af32d5a79a4&lang=ru&units=metric";
//        String url1 = "https://api.openweathermap.org/data/2.5/weather?q=Ekaterinburg&appid=095a5d30d59ab4044ed09af32d5a79a4";
        WeatherApi responce = restTemplate.getForObject(url, WeatherApi.class);
        String cel[] = responce.getMain().getTemp().split(".");


//        System.out.println(restTemplate.getForObject(url, String.class));
        if ( id == 245356255 || id == 459811485){
         answer = "Сейчас " + "1.77" + "°C" +"\n" + "За окном - " + responce.getWeather()[0].getDescription()
                + "\n" + "Скорость ветра " + responce.getWind().getSpeed() + " м/с \n (Настоящая темпераптура " + responce.getMain().getTemp() +" )"  ; } else {
            answer = "Сейчас " + responce.getMain().getTemp() + "°C" +"\n" + "За окном - " + responce.getWeather()[0].getDescription()
                    + "\n" + "Скорость ветра " + responce.getWind().getSpeed() + " м/с";
        }
        return answer;
    }
    public static String getBTCUSDT(String s){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.binance.com/api/v3/avgPrice?symbol=" + s;
        BinanceVal answer  = restTemplate.getForObject(url, BinanceVal.class);
        return s + " - " + answer.getPrice();
    }
    public static String getBalance(){
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("type", "SPOT");
        parameters.put("recvWindow", "60000");


        SpotClientImpl client = new SpotClientImpl("yoCynZQ4rGgwbz36PtM2xMS2qKqLuZLZhtRBGXqswXvyw6Neyjmt3yu0CBmud5JF", "K0LxcAqzwHixu1nScaTHv1qnCrqXA8AVOSkecRTT8K6cCDvpUcyTDilP8BBaBvSN");
        String result = client.createWallet().accountSnapshot(parameters);
        BinanceSnapshot ronaldo = null;
        try {
            ronaldo = new ObjectMapper().readValue(result, BinanceSnapshot.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        String s = "";
        for (int i = 0; i < ronaldo.getSnapshotVos()[0].getData().getBalances().length; i++){
           s = s + ronaldo.getSnapshotVos()[0].getData().getBalances()[i].getAsset()  + " - ";
           s = s + ronaldo.getSnapshotVos()[0].getData().getBalances()[i].getFree() + "\n";
        }
        System.out.println(s);
        return s;
    }
    public static String getVkToken(String code){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://oauth.vk.com/access_token?client_id=51589630&client_secret=OAIVSMeO6R3K7G76owoA&redirect_uri=https://oauth.vk.com/blank.html&code=" + code;
        String responce = restTemplate.getForObject(url, String.class);
        JSONObject json = new JSONObject(responce);

        return json.getString("access_token");
    }
}
