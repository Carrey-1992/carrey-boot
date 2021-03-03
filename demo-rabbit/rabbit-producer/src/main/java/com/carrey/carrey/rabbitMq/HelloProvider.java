package com.carrey.carrey.rabbitMq;

import com.carrey.carrey.config.HelloRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloProvider implements RabbitTemplate.ConfirmCallback {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendMsg(String content) {
        //把消息放入ROUTING_KEY_HELLO对应的队列当中去，对应的是队列hello
        rabbitTemplate.convertAndSend(HelloRabbitConfig.EXCHANGE_HELLO, HelloRabbitConfig.ROUTING_KEY_HELLO, content);
    }

    /**
     * 回调
     *
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info(" 回调id:" + correlationData);
        if (ack) {
            log.info("消息成功消费");
        } else {
            log.info("消息消费失败:" + cause);
        }
    }
}
