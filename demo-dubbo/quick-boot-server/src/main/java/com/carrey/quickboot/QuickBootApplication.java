package com.carrey.quickboot;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class QuickBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickBootApplication.class, args);
    }

}
