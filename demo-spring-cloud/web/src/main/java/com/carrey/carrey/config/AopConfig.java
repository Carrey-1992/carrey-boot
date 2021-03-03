package com.carrey.carrey.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Conway
 * @className AopConfig
 * @description
 * @date 2020/11/17 5:34 下午
 */
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@ComponentScan("com.carrey.carrey.aop")
public class AopConfig {

//    @Bean
//    public AopTestAspect aopTestAspect() {
//        return new AopTestAspect();
//    }
//
//    @Bean
//    public IAopDemoTest aopDemoTest() {
//        return new AopDemoTestImpl();
//    }

}
