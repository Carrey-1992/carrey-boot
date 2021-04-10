package com.carrey.carrey.async.eventbus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

/**
 * @author Carrey
 * @className OrderEventListener
 * @description OrderEventListener
 * @date 2021/4/9 11:01 上午
 */
public class OrderEventListener {
    /**
     * 如果发送了OrderMessage消息，会进入到该函数的处理
     * @param event 消息
     */
    @Subscribe
    @AllowConcurrentEvents
    public void dealWithEvent(OrderMessage event) {
        // TODO: 收到EventTest消息之后，做相应的处理
        System.out.println("我收到了您的命令，命令内容为：" + event.getOrderContent());
    }

}
