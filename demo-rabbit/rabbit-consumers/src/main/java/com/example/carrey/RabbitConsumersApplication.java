package com.example.carrey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RabbitConsumersApplication {
  public static void main(String[] args) {
    SpringApplication.run(RabbitConsumersApplication.class, args);
  }
}
