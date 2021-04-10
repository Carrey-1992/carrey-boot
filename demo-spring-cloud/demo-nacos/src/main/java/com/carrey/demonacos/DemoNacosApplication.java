package com.carrey.demonacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DemoNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoNacosApplication.class, args);
    }

}
