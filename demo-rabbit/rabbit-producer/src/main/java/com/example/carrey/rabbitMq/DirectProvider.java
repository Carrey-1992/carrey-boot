package com.example.carrey.rabbitMq;

import com.example.carrey.config.DirectConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectProvider {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //发送信息
    public void sendMessage(String content) {

        rabbitTemplate.convertAndSend(DirectConfig.EXCHANGE_DIRECT,DirectConfig.ROUTING_KEY_DIRECT,content);
    }
}
