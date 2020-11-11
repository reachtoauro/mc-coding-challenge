package com.cityconnect.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.cityconnect.api"})
public class CityConnectApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(CityConnectApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(CityConnectApplication.class, args);
		LOGGER.info("Application started!");
	}

}
