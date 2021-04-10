package com.carrey.order.entity;

import java.util.List;

/**
 * @author Conway
 * @className Order
 * @description
 * @date 2021/4/8 11:23 上午
 */
public class Order {

    private String orderCode;

    private String orderName;

    private List<String> productCodeList;

    public Order() {
    }

    public Order(String orderCode, String orderName) {
        this.orderCode = orderCode;
        this.orderName = orderName;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public List<String> getProductCodeList() {
        return productCodeList;
    }

    public void setProductCodeList(List<String> productCodeList) {
        this.productCodeList = productCodeList;
    }
}
