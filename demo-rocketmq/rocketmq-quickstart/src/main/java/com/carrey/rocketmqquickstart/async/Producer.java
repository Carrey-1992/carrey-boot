package com.carrey.rocketmqquickstart.async;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @author Conway
 * @className Producer
 * @description
 * @date 2021/1/27 8:22 下午
 */
public class Producer {
    public static void main(String[] args) throws InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("testProducerGroup");
        producer.setNamesrvAddr("172.16.20.3:9876");
        try {
            producer.start();
            //发送单条消息
            Message msg = new Message("TOPIC_TEST","hello rocketmq".getBytes());
            producer.send(msg, new SendCallback() {
                /**
                 * 消息发送成功回调函数
                 * @param sendResult
                 */
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%s%n", sendResult);
                }

                /**
                 * 消息发送失败回调函数
                 * @param throwable
                 */
                @Override
                public void onException(Throwable throwable) {
                    throwable.printStackTrace();
                    //　消息发送失败，可以在这里做补偿，例如将消息存储到数据库，定时重试。
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            //消息发送失败，可以在这里做补偿，例如将消息存储到数据库，定时重试
        }
        Thread.sleep(3000);
        //　使用完毕后，关闭消息发送者
        // 基于 Spring Boot 的应用，在消息发送的时候并不会调用 shutdown 方法，而是等到 spring 容器停止
        producer.shutdown();
    }
}
