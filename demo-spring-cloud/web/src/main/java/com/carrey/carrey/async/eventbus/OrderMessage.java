package com.carrey.carrey.async.eventbus;

/**
 * @author Carrey
 * @className OrderMessage
 * @description OrderMessage
 * @date 2021/4/9 11:00 上午
 */
public class OrderMessage {
    /**
     * 命令对应的内容
     */
    private String orderContent;


    public String getOrderContent() {
        return orderContent;
    }

    public OrderMessage(String orderContent) {
        this.orderContent = orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }
}
