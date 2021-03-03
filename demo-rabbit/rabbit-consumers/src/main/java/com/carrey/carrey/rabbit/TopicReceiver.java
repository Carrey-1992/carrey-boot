package com.carrey.carrey.rabbit;

import com.carrey.carrey.config.TopicConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TopicReceiver {

    @RabbitListener(queues = TopicConfig.MAIN_QUEUE)
    public void receiver1(String content) {
        log.info("TopicReceiver1接收处理队列当中的消息：[{}]",content);
    }

    @RabbitListener(queues = TopicConfig.WOMAN_QUEUE)
    public void receiver2(String content) {
        log.info("TopicReceiver2接收处理队列当中的消息：[{}]",content);
    }
}
