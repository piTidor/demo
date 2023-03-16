package com.example.demo;

import com.example.demo.apiService.FirstApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
		System.out.println(FirstApi.getPogoda());
		FirstApi.getAnekdot();
	}

}
