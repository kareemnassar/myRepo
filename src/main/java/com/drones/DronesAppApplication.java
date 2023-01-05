package com.drones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DronesAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DronesAppApplication.class, args);
	}

}
