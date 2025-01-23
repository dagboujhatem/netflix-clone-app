package com.albinus.netflix.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.albinus.netflix.lib.models")
public class NetflixMovieServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixMovieServiceApplication.class, args);
	}

}
