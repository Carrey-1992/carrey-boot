package com.carrey.carrey;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Conway
 * @className Application
 * @description
 * @date 2021/1/18 下午2:28
 */
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }

    @Bean
    public Redisson redisson() {
        // 此为单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.0.60:6379").setDatabase(0);
        /*config.useClusterServers()
                .addNodeAddress("redis://192.168.0.61:8001")
                .addNodeAddress("redis://192.168.0.62:8002")
                .addNodeAddress("redis://192.168.0.63:8003")
                .addNodeAddress("redis://192.168.0.61:8004")
                .addNodeAddress("redis://192.168.0.62:8005")
                .addNodeAddress("redis://192.168.0.63:8006");*/
        return (Redisson) Redisson.create(config);
    }
}
