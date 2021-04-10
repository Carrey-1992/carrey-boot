package com.carrey.carrey.queue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Carrey
 * @className Order
 * @description Order
 * @date 2021/4/9 1:48 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    // 订单编号
    private String orderId;
    // 订单金额
    private Double orderMoney;

}
