package com.Vargas.RhApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RhAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RhAppApplication.class, args);
		System.out.println("seu servidor esta rodando em http://localhost:3000");
	}

}
