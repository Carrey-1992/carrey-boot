package com.carrey.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsulOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulOrderApplication.class, args);
    }

}
