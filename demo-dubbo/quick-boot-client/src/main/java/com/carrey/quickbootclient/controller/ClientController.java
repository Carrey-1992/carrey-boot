package com.carrey.quickbootclient.controller;

import com.carrey.client.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Carrey
 * @className ClientController
 * @description ClientController
 * @date 2021/4/11 11:17 上午
 */
@RestController
@RequestMapping("/dubbo-client")
public class ClientController {

    @Autowired
    public OrderService orderService;

    @GetMapping("/order")
    public String getOrder() {
        return orderService.getOrder();
    }
}
