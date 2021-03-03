package com.carrey.rocketmqquickstart.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragelyByCircle;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author Conway
 * @className PushConsumer
 * @description
 * @date 2021/1/28 9:34 下午
 */
public class PushConsumer {

    public static void main(String[] args) throws InterruptedException, MQClientException {
        DefaultMQPushConsumer consumer = new
                DefaultMQPushConsumer("dw_test_consumer_6");
        consumer.setNamesrvAddr("172.16.20.3:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("order_topic", "*");
        //通过调用 setAllocateMessageQueueStrategy 指定队列负载机制
        consumer.setAllocateMessageQueueStrategy(new
                AllocateMessageQueueAveragelyByCircle());
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                try {
                    System.out.printf("%s Receive New Messages: %s %n",
                            Thread.currentThread().getName(), msgs);
                    //消费成功
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                } catch (Throwable e) {
                    e.printStackTrace();
                    //需要重试
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }
        });
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }

}
