package com.carrey.demoribbon.controller;

import com.carrey.demoribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/hello/{name}")
    public String helloWord(@PathVariable(value = "name") String name){
        return helloService.helloword(name);
    }
}
