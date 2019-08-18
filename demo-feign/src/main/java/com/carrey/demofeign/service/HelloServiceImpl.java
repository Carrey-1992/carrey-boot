package com.carrey.demofeign.service;

import com.carrey.demofeign.client.HelloWordClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService{

    @Autowired
    private HelloWordClient helloWordClient;

    @Override
    public String hello(String name) {
        return helloWordClient.home(name);
    }
}
