package com.carrey.carrey.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    /**
     * 定义扇出（广播）交换器
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout-exchange");
    }

    /**
     * 定义自动删除匿名队列
     *
     * AnonymousQueue这个类是代表一个匿名的、不耐用的、独占的、自动删除的队列
     * @return
     */
    @Bean
    public Queue autoDeleteQueue0() {
        return new AnonymousQueue();
    }

    /**
     * 定义自动删除匿名队列
     */
    @Bean
    public Queue autoDeleteQueue1() {
        return new AnonymousQueue();
    }

    /**
     * 把队列绑定到扇出（广播）交换器
     * @param fanoutExchange 扇出（广播）交换器
     * @param autoDeleteQueue0 自动删除队列
     */
    @Bean
    public Binding binding0(@Autowired FanoutExchange fanoutExchange,@Autowired Queue autoDeleteQueue0) {
        return BindingBuilder.bind(autoDeleteQueue0).to(fanoutExchange);
    }

    /**
     * 把队列绑定到扇出（广播）交换器
     *
     * @param fanoutExchange 扇出（广播）交换器
     * @param autoDeleteQueue1 自动删除队列
     */
    @Bean
    public Binding binding1(@Autowired FanoutExchange fanoutExchange,@Autowired Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1).to(fanoutExchange);
    }
}
