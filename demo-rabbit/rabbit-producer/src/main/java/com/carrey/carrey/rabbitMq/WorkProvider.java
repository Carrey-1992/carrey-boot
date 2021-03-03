package com.carrey.carrey.rabbitMq;

import com.carrey.carrey.config.WorkRabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkProvider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //发送信息
    public void sendMessage(String content) {

        rabbitTemplate.convertAndSend(WorkRabbitConfig.EXCHANGE_WORK,WorkRabbitConfig.ROUTING_KEY_WORK,content);
    }
}
