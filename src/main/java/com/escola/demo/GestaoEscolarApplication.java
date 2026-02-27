package com.escola.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GestaoEscolarApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoEscolarApplication.class, args);
	}
}
