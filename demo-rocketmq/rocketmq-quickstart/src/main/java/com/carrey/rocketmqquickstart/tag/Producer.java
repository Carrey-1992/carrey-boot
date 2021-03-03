package com.carrey.rocketmqquickstart.tag;

import com.alibaba.fastjson.JSON;
import com.carrey.rocketmqquickstart.order.Order;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * @author Conway
 * @className Producer
 * @description tag练习
 * @date 2021/1/27 9:13 下午
 * <p>
 * RocketMQ Tag 使用场景
 * RocketMQ 可以为 Topic 设置 Tag（标签），这样消费端可以对 Topic 中的消息基于 Tag 进行过滤，即选择性的对 Topic 中的消息进行处理。
 * <p>
 * 例如一个订单的全生命流程：创建订单、待支付、支付完成、商家审核，商家发货、买家发货，订单每一个状态的变更都会向主题 order_topic 发送消息，但不同下游系统只关注订单流中某几个阶段的消息，并不是需要处理所有消息。
 * <p>
 * 例如有如下两个场景：
 * <p>
 * 活动模块，只要用户下单并成功支付，就发放一张优惠券；
 * 物流模块，只需关注订单审核通过后，就需要创建物流信息，选择供应商。
 * 故会创建两个消费组 order_topic_activity_consumer、order_topic_logistics_consumer，但这些消费组又无需处理全部消息，这个时候 Tag 机制就派上用场了。
 * <p>
 * 在消息发送时，例如创建订单时，发送的消息时，设置 Tag 为 c，而支付成功时创建的消息为 w。然后各个场景的消费者按需要订阅 Topic 时指定 Tag。
 */
public class Producer {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("dw_test_producer_group");
        producer.setNamesrvAddr("172.16.20.3:9876");
        producer.start();
        // 订单实体
        Order order = new Order();
        order.setId(1001L);
        order.setOrderNo("2020072823270500003");
        order.setBuyerId(1L);
        order.setSellerId(2L);
        order.setTotalPrice(10000L);
        order.setStatus(0);
        Message msg = new Message("dw_test", "c", "2020072823270500003",
                JSON.toJSONString(order).getBytes());
        System.out.printf("%s%n", producer.send(msg));
        order.setStatus(1);
        msg = new Message("dw_test", "w", "2020072823270500003",
                JSON.toJSONString(order).getBytes());
        System.out.printf("%s%n", producer.send(msg));
        producer.shutdown();
    }
}
