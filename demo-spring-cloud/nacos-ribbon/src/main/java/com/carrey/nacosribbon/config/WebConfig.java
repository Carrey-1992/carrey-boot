package com.carrey.nacosribbon.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by smlz on 2019/11/18.
 */
@Configuration
public class WebConfig {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate( ) {
        return new RestTemplate();
    }
}
