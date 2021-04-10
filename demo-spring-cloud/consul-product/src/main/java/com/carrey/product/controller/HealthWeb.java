package com.carrey.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Conway
 * @className HealthWeb
 * @description
 * @date 2021/4/7 6:02 下午
 */
@RestController
@RequestMapping("/consul")
public class HealthWeb {
    @GetMapping(value = "health")
    public String health() {
        return "check health";
    }
}
