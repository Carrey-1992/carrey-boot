package com.carrey.quickbootclient.service.impl;

import com.alibaba.fastjson.JSON;
import com.carrey.client.OrderService;
import com.carrey.client.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author Carrey
 * @className ClientServiceImpl
 * @description ClientServiceImpl
 * @date 2021/4/11 11:19 上午
 */
@Service
@Component
public class OrderServiceImpl implements OrderService {

    @Reference
    private UserService userService;

    @Override
    public String getOrder() {
        return JSON.toJSONString(userService.getUser(1));
    }
}
