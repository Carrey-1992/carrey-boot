package com.carrey.rocketmqquickstart.order;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.util.CollectionUtils;

/**
 * @author Conway
 * @className Producer
 * @description 顺序发送
 * @date 2021/1/27 8:44 下午
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("dw_test_producer_group");
        producer.setNamesrvAddr("172.16.20.3:9876");
        producer.start();
        //订单实体
        Order order = new Order();
        order.setId(1001L);
        order.setOrderNo("2020072823270500001");
        order.setBuyerId(1L);
        order.setSellerId(1L);
        order.setTotalPrice(10000L);
        order.setStatus(0);
        System.out.printf("%s%n", sendMsg(producer, order));
        //订单状态发送变更
        order.setStatus(1);
        System.out.printf("%s%n", sendMsg(producer, order));
        producer.shutdown();
    }

    private static SendResult sendMsg(DefaultMQProducer producer, Order order) throws Exception{
        //这里为了方便查找消息，在构建消息的时候，使用订单编号为key，这样可以通过订单编号查找消息。
        Message msg = new Message("order_topic",null, order.getOrderNo(),
                JSON.toJSONString(order).getBytes());

        //多个key，用空格分开即可
        // Message msg = new Message("order_topic",null, "2020072823270500002 ODS0002",JSON.toJSONString(order).getBytes());

        //自定义队列负载机制
        //注意使用了MessageQueueSelector，那消息发送的重试机制将失效，
        // 即 RocketMQ 客户端并不会重试，消息发送的高可用需要由业务方来保证，
        // 典型的就是消息发送失败后存在数据库中，然后定时调度，最终将消息发送到 MQ
        return producer.send(msg, (mqs, message, arg) -> {
            //根据订单编号hashcode进行队列选择
            if(CollectionUtils.isEmpty(mqs)){
                return null;
            }
            //对订单的hashcode取绝对值后取模
            int index = Math.abs(arg.hashCode()) % mqs.size();
            return mqs.get(Math.max(index, 0));
        },order.getOrderNo());
    }
}
