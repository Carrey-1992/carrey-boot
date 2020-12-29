package com.carrey.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Conway
 * @className CarreyConfig
 * @description
 * @date 2020/12/2 下午5:31
 */
@Configuration
@ComponentScan("com.carrey.demo")
public class CarreyConfig extends WebMvcConfigurationSupport {
}
