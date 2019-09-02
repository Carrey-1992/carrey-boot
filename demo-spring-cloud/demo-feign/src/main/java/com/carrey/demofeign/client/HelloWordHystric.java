package com.carrey.demofeign.client;

import org.springframework.stereotype.Component;

@Component
public class HelloWordHystric implements HelloWordClient{
    @Override
    public String home(String name) {
        return "sorry "+name;
    }
}
