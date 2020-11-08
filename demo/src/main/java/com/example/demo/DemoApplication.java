package com.example.demo;

import com.example.demo.api.models.polygonHandle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		polygonHandle.read();
		SpringApplication.run(DemoApplication.class, args);
	}

}
