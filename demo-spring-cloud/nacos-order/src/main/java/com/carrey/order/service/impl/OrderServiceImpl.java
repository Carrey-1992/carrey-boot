package com.carrey.order.service.impl;

import com.carrey.order.dto.OrderDetailDTO;
import com.carrey.order.entity.Order;
import com.carrey.order.service.OrderService;
import com.carrey.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Conway
 * @className OrderServiceImpl
 * @description
 * @date 2021/4/8 11:29 上午
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public OrderDetailDTO getOrderDetailDTO(Order order) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setOrderCode(order.getOrderCode());
        orderDetailDTO.setOrderName(order.getOrderName());
        List<Product> productList = order.getProductCodeList().stream()
                .map(productCode ->
                        restTemplate.getForObject("http://nacos-product/product/detail/" + productCode, Product.class))
                .collect(Collectors.toList());
        orderDetailDTO.setProductList(productList);
        return orderDetailDTO;
    }
}
