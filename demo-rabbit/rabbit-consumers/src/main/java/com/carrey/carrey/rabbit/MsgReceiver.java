package com.carrey.carrey.rabbit;

import com.carrey.carrey.config.HelloRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = HelloRabbitConfig.QUEUE_HELLO)
@Slf4j
public class MsgReceiver {

  @RabbitHandler
  public void process(String content) {
    log.info("接收处理队列A当中的消息：[{}]",content);
  }
}
