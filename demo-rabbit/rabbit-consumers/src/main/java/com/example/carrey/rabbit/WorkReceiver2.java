package com.example.carrey.rabbit;

import com.example.carrey.config.RabbitConfig;
import com.example.carrey.config.WorkRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = WorkRabbitConfig.QUEUE_WORK,containerFactory = "rabbitListenerContainerFactory")
@Slf4j
public class WorkReceiver2 {
    @RabbitHandler
    public void process(String content) {
        log.info("WorkReceiver2接收处理队列Work当中的消息：[{}]",content);
        //模拟业务处理，0.5秒
        try {
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
