package com.carrey.carrey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient
public class WebApplication {
  public static void main(String[] args) {
    SpringApplication.run(WebApplication.class, args);
  }
}
