package com.carrey.quickbootclient;

import com.carrey.client.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableDubbo
@SpringBootApplication
public class QuickBootClientApplication {

    @Reference
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(QuickBootClientApplication.class, args);
    }

    @Bean
    public ApplicationRunner getBean() {
        return args -> {
            System.out.println(userService.getUser(1));
        };
    }
}
