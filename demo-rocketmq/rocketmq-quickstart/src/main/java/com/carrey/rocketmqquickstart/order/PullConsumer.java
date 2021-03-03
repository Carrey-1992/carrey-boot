package com.carrey.rocketmqquickstart.order;


import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Conway
 * @className PullConsumer
 * @description
 * @date 2021/1/28 9:24 下午
 */
public class PullConsumer {

    public static void main(String[] args) throws Exception {
        Semaphore semaphore = new Semaphore();
        Thread t = new Thread(new Task(semaphore));
        t.start();
        CountDownLatch cdh = new CountDownLatch(1);
        try {
            //程序运行 120s　后结束
            cdh.await(120 * 1000, TimeUnit.MILLISECONDS);
        } finally {
            semaphore.running = false;
        }
    }

    /**
     * 消息拉取核心实现逻辑
     */
    static class Task implements Runnable {
        Semaphore s = new Semaphore();
        public Task(Semaphore s ) {
            this.s = s;
        }
        @Override
        public void run() {
            try {
                DefaultMQPullConsumer consumer = new
                        DefaultMQPullConsumer("dw_pull_consumer");
                consumer.setNamesrvAddr("172.16.20.3:9876");
                consumer.start();
                Map<MessageQueue, Long> offsetTable = new HashMap<MessageQueue, Long>();
                Set<MessageQueue> msgQueueList = consumer.
                        fetchSubscribeMessageQueues("order_topic"); // 获取该 Topic 的所有队列
                if(msgQueueList != null && !msgQueueList.isEmpty()) {
                    boolean noFoundFlag = false;
                    while(this.s.running) {
                        if(noFoundFlag) { //　没有找到消息，暂停一下消费
                            Thread.sleep(1000);
                        }
                        for( MessageQueue q : msgQueueList ) {
                            PullResult pullResult = consumer.pull(q, "*",decivedPulloffset(offsetTable
                                    , q, consumer) , 3000);
                            System.out.println("pullStatus:" + pullResult.getPullStatus());
                            switch (pullResult.getPullStatus()) {
                                case FOUND:
                                    doSomething(pullResult.getMsgFoundList());
                                    break;
                                case NO_MATCHED_MSG:
                                    break;
                                case NO_NEW_MSG:
                                case OFFSET_ILLEGAL:
                                    noFoundFlag = true;
                                    break;
                                default:
                                    continue ;
                            }
                            //提交位点
                            consumer.updateConsumeOffset(q,
                                    pullResult.getNextBeginOffset());
                        }
                        System.out.println("balacne queue is empty: " + consumer.
                                fetchMessageQueuesInBalance("order_topic").isEmpty());
                    }
                } else {
                    System.out.println("end,because queue is enmpty");
                }
                consumer.shutdown();
                System.out.println("consumer shutdown");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
    /** 拉取到消息后具体的处理逻辑　*/
    private static void doSomething(List<MessageExt> msgs) {
        System.out.println("本次拉取到的消息条数:" + msgs.size());
    }
    public static long decivedPulloffset(Map<MessageQueue, Long> offsetTable,
                                         MessageQueue queue, DefaultMQPullConsumer consumer) throws Exception {
        long offset = consumer.fetchConsumeOffset(queue, false);
        if(offset < 0 ) {
            offset = 0;
        }
        System.out.println("offset:" + offset);
        return offset;
    }
    static class Semaphore {
        public volatile boolean running = true;
    }
}
