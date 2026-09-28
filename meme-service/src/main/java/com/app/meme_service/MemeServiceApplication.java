package com.app.meme_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients
public class MemeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemeServiceApplication.class, args);
	}

}
