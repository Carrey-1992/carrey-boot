package com.carrey.demoshardingsphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.carrey.demoshardingsphere.dao")
@SpringBootApplication
public class DemoShardingsphereApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoShardingsphereApplication.class, args);
    }

}
