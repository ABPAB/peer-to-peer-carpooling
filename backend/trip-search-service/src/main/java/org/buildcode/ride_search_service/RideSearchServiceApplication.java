package org.buildcode.ride_search_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RideSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideSearchServiceApplication.class, args);
		System.out.println("Ride Search Service started...");
	}
}