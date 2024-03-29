package com.vany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebservicesApplication.class, args);
	}

}
