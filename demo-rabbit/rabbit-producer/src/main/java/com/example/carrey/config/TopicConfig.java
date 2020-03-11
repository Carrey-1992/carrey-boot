package com.example.carrey.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {
    //绑定键
    public final static String MAIN_QUEUE = "topic.MAIN_QUEUE";
    public final static String WOMAN_QUEUE = "topic.WOMAN_QUEUE";
    public final static String EXCHANGE = "topicExchange";

    @Bean
    public Queue firstQueue() {
        return new Queue(MAIN_QUEUE);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(WOMAN_QUEUE);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    //将firstQueue和topicExchange绑定,而且绑定的键值为topic.MAIN_QUEUE
    //这样只要是消息携带的路由键是topic.MAIN_QUEUE,才会分发到该队列
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(MAIN_QUEUE);
    }

    //将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }

}
