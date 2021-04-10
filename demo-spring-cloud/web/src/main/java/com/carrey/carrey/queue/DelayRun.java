package com.carrey.carrey.queue;

import java.util.concurrent.DelayQueue;

/**
 * @author Carrey
 * @className DelayRun
 * @description DelayRun
 * @date 2021/4/9 1:53 下午
 */
public class DelayRun {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayItem<Order>> queue = new DelayQueue<>();
        /**
         * 这里模拟淘宝、京东、苏宁的订单，淘宝是5秒到期，京东是10秒到期，苏宁是15秒到期
         */
        // 淘宝订单插入
        Order tbOrder = new Order("tb001", 9.9);
        DelayItem<Order> DelayItemTb = new DelayItem<Order>(5000, tbOrder);
        queue.offer(DelayItemTb);
        System.out.println("淘宝订单5秒后过期：" + tbOrder.getOrderId());

        // 京东订单插入
        Order jdOrder = new Order("jd002", 19.9);
        DelayItem<Order> DelayItemJd = new DelayItem<Order>(5000, jdOrder);
        queue.offer(DelayItemJd);
        System.out.println("京东订单10秒后过期：" + jdOrder.getOrderId());

        // 苏宁订单插入
        Order snOrder = new Order("sn003", 29.9);
        DelayItem<Order> DelayItemSn = new DelayItem<Order>(5000, snOrder);
        queue.offer(DelayItemSn);
        System.out.println("苏宁订单15秒后过期：" + tbOrder.getOrderId());
        long initTime = 0;
        while (true) {
            long beginTime = System.currentTimeMillis();
            System.out.println("开始从延迟队列中获取元素，当前时间戳为："+ beginTime);
            DelayItem<Order> delayItem = queue.take();
            Order entity = delayItem.getEntity();
            long endTime = System.currentTimeMillis();
            if (initTime != 0) {
                System.out.println("上一次获取元素后到现在的时间差为：" + (endTime - initTime));
            }
            System.out.println("从延迟队列中获取到订单："+entity.getOrderId()+"耗时："+(endTime - beginTime));
            initTime = endTime;
            Thread.sleep(1000);
        }
    }
}
