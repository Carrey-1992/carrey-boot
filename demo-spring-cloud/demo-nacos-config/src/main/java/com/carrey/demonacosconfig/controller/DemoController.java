package com.carrey.demonacosconfig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Carrey
 * @className demoController
 * @description demoController
 * @date 2021/4/11 1:36 下午
 */
@RefreshScope
@RestController
@RequestMapping("/nacos-config")
public class DemoController {

    @Autowired
    private DemoProperties demoProperties;

    @GetMapping("/demo")
    public String demo() {
        return demoProperties.getTest();
    }
}
