package com.carrey.order.controller;

import com.carrey.order.dto.OrderDetailDTO;
import com.carrey.order.entity.Order;
import com.carrey.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Conway
 * @className OrderController
 * @description
 * @date 2021/4/8 11:21 上午
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/detail/order-code/{orderCode}")
    public OrderDetailDTO getOrderDetailDTO(@PathVariable(value = "orderCode") String orderCode) {
        Order order = new Order("0001","订单测试0001");
        List<String> productCodeList = new ArrayList<>();
        productCodeList.add("0001");
        order.setProductCodeList(productCodeList);
        if (order.getOrderCode().equals(orderCode)) {
            return orderService.getOrderDetailDTO(order);
        }
        return null;
    }

}
