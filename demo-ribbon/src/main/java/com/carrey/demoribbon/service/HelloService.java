package com.carrey.demoribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    public String helloword(String name) {
        return restTemplate.getForObject("http://WEB/demo-web/"+name,String.class);
    }
}
