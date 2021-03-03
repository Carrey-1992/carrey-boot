package com.carrey.demokafka.quickstart;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Conway
 * @className Order
 * @description
 * @date 2021/2/22 5:34 下午
 */
@Data
@NoArgsConstructor
public class Order {

    private Integer orderId;

    private Integer dd;

    private Integer num;

    private Double price;

    public Order(Integer orderId, Integer dd, Integer num, Double price) {
        this.orderId = orderId;
        this.dd = dd;
        this.num = num;
        this.price = price;
    }
}
