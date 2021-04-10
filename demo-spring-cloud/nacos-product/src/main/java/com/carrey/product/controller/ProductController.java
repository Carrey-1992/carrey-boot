package com.carrey.product.controller;

import com.carrey.product.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Conway
 * @className ProductController
 * @description
 * @date 2021/4/8 11:47 上午
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/detail/{productCode}")
    public Product getDetail(@PathVariable(value = "productCode") String productCode) {
        Product product = new Product("0001","商品ribbo测试");
        if (product.getProductCode().equals(productCode)) {
            return product;
        }
        return null;
    }

}
