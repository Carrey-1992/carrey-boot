package com.carrey.product.entity;

/**
 * @author Conway
 * @className Order
 * @description
 * @date 2021/4/8 11:23 上午
 */
public class Product {

    private String productCode;

    private String productName;

    public Product() {
    }

    public Product(String orderCode, String orderName) {
        this.productCode = orderCode;
        this.productName = orderName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
