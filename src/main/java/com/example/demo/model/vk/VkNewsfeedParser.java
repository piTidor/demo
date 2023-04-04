package com.example.demo.model.vk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import com.example.demo.model.table.ContentPool;
import com.example.demo.model.table.ContentPost;
import com.example.demo.model.table.VkGroup;
import com.example.demo.model.weatherOneDay.WeatherApi;
import com.example.demo.service.FirstBot;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

public class VkNewsfeedParser {
    static String token = "vk1.a.CpX6XH1dwHAMTargstHIlRx6pa1nBeZgmeNaoMk-qo7zqLDhvkeL_SXamjEVh5boruJmzpFzpUlU22lmrkbSwpFtqFZeEOPjkvTrEohXueDdF1BIHG0VJehcaR8N50PMwHqfQvSINrSNW5AP3vEp8OJU6Wr-Qc2hFCUxUI3xKeGbl-Cd9M5t5YNOWueUEzhoLRk_hx6AHfLFXHG1qhDDzw";
    public static String postRuss(){

        String url = "https://api.vk.com/method/wall.post?access_token=vk1.a.CpX6XH1dwHAMTargstHIlRx6pa1nBeZgmeNaoMk-qo7zqLDhvkeL_SXamjEVh5boruJmzpFzpUlU22lmrkbSwpFtqFZeEOPjkvTrEohXueDdF1BIHG0VJehcaR8N50PMwHqfQvSINrSNW5AP3vEp8OJU6Wr-Qc2hFCUxUI3xKeGbl-Cd9M5t5YNOWueUEzhoLRk_hx6AHfLFXHG1qhDDzw&owner_id=-166253156" +
             "&attachments=" +  "" +  "&v=5.131";
        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();

        String responce = restTemplate.getForObject(url, String.class);

        return responce;
    }
    public static ContentPost[] getWall(String s){


            String url = "https://api.vk.com/method/wall.get?access_token=vk1.a.CpX6XH1dwHAMTargstHIlRx6pa1nBeZgmeNaoMk-qo7zqLDhvkeL_SXamjEVh5boruJmzpFzpUlU22lmrkbSwpFtqFZeEOPjkvTrEohXueDdF1BIHG0VJehcaR8N50PMwHqfQvSINrSNW5AP3vEp8OJU6Wr-Qc2hFCUxUI3xKeGbl-Cd9M5t5YNOWueUEzhoLRk_hx6AHfLFXHG1qhDDzw&count=10&owner_id=" +
                  s  + "&v=5.131";
            RestTemplate restTemplate = new RestTemplate();

            String responce = restTemplate.getForObject(url, String.class);

            JSONObject json = new JSONObject(responce);
            int siz = json.getJSONObject("response").getJSONArray("items").getJSONObject(0).getJSONArray("attachments").length();
            ContentPost[] cpArray = new ContentPost[siz];
            int answer4 = json.getJSONObject("response").getJSONArray("items").getJSONObject(0).getInt("date");
            for (int g = 0; g < siz; g++) {
                        JSONObject gh = json.getJSONObject("response").getJSONArray("items").getJSONObject(0).getJSONArray("attachments").getJSONObject(g);
                        String answer1 = gh.getString("type");
                        if (answer1.equals("photo") || answer1.equals("video")){
                        int answer2 = gh.getJSONObject(answer1).getInt("id");
                        int answer3 = gh.getJSONObject(answer1).getInt("owner_id");
                            ContentPost sP = new ContentPost();
                            sP.setMedia_id(answer2);
                            sP.setType(answer1);
                            sP.setOwner_id(answer3);
                            cpArray[g] = sP;
                        }
            }


        return cpArray;

    }
    public static String[] pars(String s , String cont, String startNews) {
        try {
            // Создание объекта URL для получения доступа к API
            URL url = new URL("https://api.vk.com/method/newsfeed.get?access_token=" + s + "&filters=post" +
                    "&count=" + cont + "&start_from=" + startNews + "&v=5.131" );

            // Создание объекта HttpURLConnection для отправки запроса
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Получение ответа от сервера и чтение данных
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String response = "";
            String line;

            while ((line = in.readLine()) != null) {
                response += line;
            }

            in.close();

            // Создание объекта JSONObject для разбора данных
            JSONObject json = new JSONObject(response);

            // Получение данных из объекта json

                if (json.has("response")) {
                    System.out.println("has response");
                    JSONObject responseObj = json.getJSONObject("response");
                    for (int i = 0; i < Integer.parseInt(cont); i++) {


                            JSONObject pos = responseObj.getJSONArray("items").getJSONObject(i);
                            String textPost = pos.getString("text");

                                JSONArray ar = responseObj.getJSONArray("items").getJSONObject(i).getJSONArray("attachments");
                                System.out.println(ar.length());
                                String next = responseObj.getString("next_from");
                        int lg = ar.length() + 2;
                        String[] answer = new String[lg];
                        int ght = 0;
                                for (int g = 0; g < ar.length(); g++){
                                    JSONObject p = responseObj.getJSONArray("items").getJSONObject(i).getJSONArray("attachments").getJSONObject(g);
//                                    String pars = String.valueOf(responseObj.getJSONArray("items").getJSONObject(i).getJSONArray("attachments").getJSONObject(g));
//                                    System.out.println(pars);
//                                    System.out.println( VkNewsfeedParser.postRuss("hello", pars));
                                        if (p.getString("type").equals("photo")) {
                                            System.out.println("has photo");

                                            JSONArray ar1 = p.getJSONObject("photo")
                                                    .getJSONArray("sizes");
                                            answer[g ] = p.getJSONObject("photo")
                                            .getJSONArray("sizes").getJSONObject(ar1.length()-1).getString("url");
                                                ght ++;

                                            answer[lg- 2] = textPost;
                                            answer[lg -1] = next;

                                            System.out.println(next);
                                            if (ght == lg- 2) {
                                                System.out.println("return answer");
                                                return answer;
                                            }
                                        }
                                }

                    }
                }
            String cr[] = new String[20];
            return cr ;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}