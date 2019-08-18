package com.example.carrey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RabbitProducerApplication {
  public static void main(String[] args) {
    SpringApplication.run(RabbitProducerApplication.class, args);
  }
}
