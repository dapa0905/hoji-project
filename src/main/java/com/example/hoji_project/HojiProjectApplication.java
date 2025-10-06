package com.example.hoji_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients(basePackages = "com.example.hoji_project.service.coin")
public class HojiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HojiProjectApplication.class, args);
	}

}
