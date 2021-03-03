package com.carrey.carrey.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {
    /**
     * rabbitMq工作队列 练习队列
     */
    public static final String QUEUE_DIRECT = "direct-query";

    public static final String EXCHANGE_DIRECT = "my-mq-exchange-direct";

    public static final String ROUTING_KEY_DIRECT = "spring-boot-routingKey-direct";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_DIRECT);
    }

    @Bean
    public Queue directQueue() {
        return new Queue(QUEUE_DIRECT, true); //队列持久
    }

    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(ROUTING_KEY_DIRECT);
    }
}
