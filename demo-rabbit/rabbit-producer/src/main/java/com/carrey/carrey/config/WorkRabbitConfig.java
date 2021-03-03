package com.carrey.carrey.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkRabbitConfig {
    /**
     * rabbitMq工作队列 练习队列
     */
    public static final String QUEUE_WORK = "work-query";

    public static final String EXCHANGE_WORK = "my-mq-exchange-work";

    public static final String ROUTING_KEY_WORK = "spring-boot-routingKey-work";

    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     * HeadersExchange ：通过添加属性key-value匹配
     * DirectExchange:按照routingkey分发到指定队列
     * TopicExchange:多关键字匹配
     */
    @Bean
    public DirectExchange workExchange() {
        return new DirectExchange(EXCHANGE_WORK);
    }

    /**
     * 获取队列A
     *
     * @return
     */
    @Bean
    public Queue workQueue() {
        return new Queue(QUEUE_WORK, true); //队列持久
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(workQueue()).to(workExchange()).with(ROUTING_KEY_WORK);
    }
}
