package com.example.springbootrabbitproducer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Conway
 * @className RabbitMQConfig
 * @description
 * @date 2021/1/24 下午3:21
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange carreyBootDirectExchange1() {
        return new DirectExchange("springboot.direct.exchange1",true,false);
    }

    @Bean
    public DirectExchange carreyBootDirectExchange2() {
        return new DirectExchange("springboot.direct.exchange2",true,false);
    }

    @Bean
    public Queue carreyBootQueue1() {
        Queue queue = new Queue("carreyBootQueue1",true,false,false);
        return queue;
    }

    @Bean
    public Queue carreyBootQueue2() {
        Queue queue = new Queue("carreyBootQueue2",true,false,false);
        return queue;
    }

    @Bean
    public Binding carreyBootBinder1() {
        return BindingBuilder.bind(carreyBootQueue1()).to(carreyBootDirectExchange1()).with("springboot.key");
    }

    @Bean
    public Binding carreyBootBinder2() {
        return BindingBuilder.bind(carreyBootQueue2()).to(carreyBootDirectExchange2()).with("springboot.key2");
    }
}
