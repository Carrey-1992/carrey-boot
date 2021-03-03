package com.carrey.rocketmqquickstart.pull_consumer;

import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author Conway
 * @className LitePullConsumer
 * @description
 * @date 2021/1/29 3:31 下午
 */
public class LitePullConsumer {

    public static final boolean running = true;

    public static void main(String[] args) throws Exception {
        DefaultLitePullConsumer litePullConsumer =
                new DefaultLitePullConsumer("dw_lite_pull_consumer_test");
        litePullConsumer.setNamesrvAddr("172.16.20.3:9876");
        litePullConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        litePullConsumer.subscribe("TopicTest", "*");
        litePullConsumer.setAutoCommit(true);
        litePullConsumer.start();

        try {
            while (running) {
                List<MessageExt> messageExts = litePullConsumer.poll();
                doConsumeSomething(messageExts);
            }
        } finally {
            litePullConsumer.shutdown();
        }
    }

    private static void doConsumeSomething(List<MessageExt> messageExts) {
        // 真正的业务处理
        System.out.printf("%s%n", messageExts);
    }
}
