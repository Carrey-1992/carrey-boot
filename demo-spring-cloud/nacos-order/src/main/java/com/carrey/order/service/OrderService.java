package com.carrey.order.service;

import com.carrey.order.dto.OrderDetailDTO;
import com.carrey.order.entity.Order;

public interface OrderService {

    /**
     * 通过Ribbon获取
     * @return
     */
    OrderDetailDTO getOrderDetailDTO(Order order);
}
