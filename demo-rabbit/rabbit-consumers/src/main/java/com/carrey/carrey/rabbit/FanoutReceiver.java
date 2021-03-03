package com.carrey.carrey.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutReceiver {

    @RabbitListener(queues = "#{autoDeleteQueue0.name}")
    public void receiver0(String str) {
        System.out.println("FanoutReceiver0++++++++++:" + str);
    }

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receiver1(String str) {
        System.out.println("FanoutReceiver1++++++++++:" + str);
    }
}
