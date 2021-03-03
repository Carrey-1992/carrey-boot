package com.carrey.carrey.rabbitMq;

import com.carrey.carrey.config.TopicConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicProvider2 {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //发送信息
    public void sendMessage(String content) {

        rabbitTemplate.convertAndSend(TopicConfig.EXCHANGE,"topic.WOMAN_QUEUE",content);
    }

}
