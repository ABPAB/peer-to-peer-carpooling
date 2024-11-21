package org.buildcode.ride_search_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RideSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideSearchServiceApplication.class, args);
		System.out.println("Ride Search Service started...");
	}
}