package com.carrey.order.dto;

import com.carrey.product.entity.Product;

import java.util.List;

/**
 * @author Conway
 * @className OrderDetailDTO
 * @description
 * @date 2021/4/8 11:37 上午
 */
public class OrderDetailDTO {

    private String orderCode;

    private String orderName;

    private List<Product> productList;

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

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
