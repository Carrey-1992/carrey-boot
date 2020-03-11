package com.example.carrey.rabbitMq;

import com.example.carrey.config.TopicConfig;
import com.example.carrey.config.WorkRabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicProvider1 {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //发送信息
    public void sendMessage(String content) {

        rabbitTemplate.convertAndSend(TopicConfig.EXCHANGE,TopicConfig.MAIN_QUEUE,content);
    }

}
