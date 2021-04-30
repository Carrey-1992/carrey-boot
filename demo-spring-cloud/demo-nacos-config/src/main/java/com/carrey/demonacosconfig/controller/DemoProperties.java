package com.carrey.demonacosconfig.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Carrey
 * @className DemoProperties
 * @description DemoProperties
 * @date 2021/4/11 1:37 下午
 */
@Component
@ConfigurationProperties("nacos")
public class DemoProperties {

    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
