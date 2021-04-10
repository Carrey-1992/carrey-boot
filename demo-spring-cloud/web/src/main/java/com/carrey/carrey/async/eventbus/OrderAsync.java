package com.carrey.carrey.async.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executors;

/**
 * @author Carrey
 * @className OrderAsync
 * @description OrderAsync
 * @date 2021/4/9 11:02 上午
 */
public class OrderAsync {
    public static void main(String[] args) {
        for (int i = 0 ; i<100; i++) {
            // 定义一个EventBus对象，因为我这里是测试，才这样写的。实际你应该定义一个单例获取其他的方式
            EventBus eventBus = new AsyncEventBus(Executors.newSingleThreadExecutor());
            // 注册监听者
            eventBus.register(new OrderEventListener());
            // 发布消息
            eventBus.post(new OrderMessage(i+""));
            System.out.println("发送完第"+i+"个消息了");
        }

    }
}
