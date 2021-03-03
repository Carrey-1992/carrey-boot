package com.carrey.carrey.rabbitMq;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutProvider {
    @Autowired
    private FanoutExchange fanoutExchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message) {
        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", message);
    }
}
