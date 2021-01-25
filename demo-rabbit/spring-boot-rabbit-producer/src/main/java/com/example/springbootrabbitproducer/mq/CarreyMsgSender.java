package com.example.springbootrabbitproducer.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * @author Conway
 * @className CarreyMsgSender
 * @description
 * @date 2021/1/24 下午3:24
 */
@Component
public class CarreyMsgSender implements InitializingBean {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg1(Object msg, Map<String,Object> msgProp) throws Exception {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());


        rabbitTemplate.convertAndSend("springboot.direct.exchange1","springboot.key",msg,correlationData);

    }

    public void sendMsg2(Object msg, Map<String,Object> msgProp) throws Exception {

        Message message = MessageBuilder.withBody(msg.toString().getBytes("UTF-8"))
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN).build();


        //构建correlationData 用于做可靠性投递得,ID:必须为全局唯一的 根据业务规则
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());


        rabbitTemplate.convertAndSend("springboot.direct.exchange2","springboot.key2",message,correlationData);
    }

    public void sendMsg3(Object msg, Map<String,Object> msgProp) throws Exception {


        //构建correlationData 用于做可靠性投递得,ID:必须为全局唯一的 根据业务规则
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());


        rabbitTemplate.convertAndSend("springboot.direct.exchange3","springboot.key3",msg,correlationData);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //开启确认模式
        rabbitTemplate.setConfirmCallback(new CarreyConfirmCallBack());

        //开启消息可达监听
        rabbitTemplate.setReturnCallback(new CarreyRetrunCallBack());
    }
}
