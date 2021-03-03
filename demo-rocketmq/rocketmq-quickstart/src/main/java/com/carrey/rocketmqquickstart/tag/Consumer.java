package com.carrey.rocketmqquickstart.tag;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author Conway
 * @className Consumer
 * @description
 * @date 2021/1/27 9:16 下午
 */
public class Consumer {
    public static void main(String[] args) throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order_topic_activity_consumer");
        consumer.setNamesrvAddr("172.16.20.3:9876");
        //从第一偏移开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //订阅topic为dw_test，tag为c的消息
        consumer.subscribe("dw_test","c");
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            //消费监听
            System.out.printf("%s Receive New Messages: %s %n",
                    Thread.currentThread().getName(), msgs);
            for (MessageExt msg : msgs){
                System.out.println(new String(msg.getBody()));
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}
