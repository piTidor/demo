package com.example.demo;

import com.binance.connector.client.impl.SpotClientImpl;

import com.example.demo.model.WeatherParser;
import com.example.demo.model.binancewalet.BinanceSnapshot;

import com.example.demo.model.table.Message;
import com.example.demo.model.table.TelegramUser;
import com.example.demo.model.vk.VkNewsfeedParser;
import com.example.demo.repo.MessRepo;
import com.example.demo.repo.VkGroupRepo;
import com.example.demo.service.FirstBot;
import com.example.demo.service.NewCkass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedInputStream;
import java.io.Console;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.LinkedHashMap;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {



		SpringApplication.run(DemoApplication.class, args);









	}

}
