package com.carrey.demoseluth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DemoSleuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSleuthApplication.class, args);
	}

}
