package com.example.springbootrabbitproducer.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * 消息接受组件
 * Created by smlz on 2019/10/10.
 */
@Component
@Slf4j
public class CarreyMsgReceiver {


    @RabbitListener(queues = {"carreyBootQueue1"})
    @RabbitHandler
    public void consumerMsg1(String msg,Message message, Channel channel) throws IOException {

        log.info("消费消息:{}",msg);
        //手工签收
        Long deliveryTag = (Long) message.getMessageProperties().getDeliveryTag();
        log.info("接受deliveryTag1:{}",deliveryTag);
        channel.basicNack(deliveryTag,false,false);
    }


    @RabbitListener(queues = {"carreyBootQueue2"})
    @RabbitHandler
    public void consumerMsg2(String msg,Message message, Channel channel) throws IOException {

        log.info("消费消息:{}",msg);
        //手工签收
        Long deliveryTag = (Long) message.getMessageProperties().getDeliveryTag();
        log.info("接受deliveryTag2:{}",deliveryTag);
        channel.basicReject(deliveryTag,false);
    }


}
