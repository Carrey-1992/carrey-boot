package com.carrey.carrey.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
 * Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。
 * Queue:消息的载体,每个消息都会被投到一个或多个队列。
 * Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
 * Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
 * vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
 * Producer:消息生产者,就是投递消息的程序.
 * Consumer:消息消费者,就是接受消息的程序.
 * Channel:消息通道,在客户端的每个连接里,可建立多个channel.
 */
@Configuration
@Slf4j
public class HelloRabbitConfig {


    public static final String EXCHANGE_HELLO = "my-mq-exchange-hello";

    /**
     * rabbitMq初始化队列
     */
    public static final String QUEUE_HELLO = "hello";


    public static final String ROUTING_KEY_HELLO = "spring-boot-routingKey-hello";


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
    public DirectExchange helloExchange() {
        return new DirectExchange(EXCHANGE_HELLO);
    }

    /**
     * 获取队列A
     *
     * @return
     */
    @Bean
    public Queue helloQueue() {
        return new Queue(QUEUE_HELLO, true); //队列持久
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(helloQueue()).to(helloExchange()).with(HelloRabbitConfig.ROUTING_KEY_HELLO);
    }

}
