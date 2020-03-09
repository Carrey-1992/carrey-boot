package com.example.carrey.controller;

import com.example.carrey.rabbitMq.FanoutSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fanout")
public class FanoutController {

    @Autowired
    private FanoutSender fanoutSender;

    @GetMapping("send/messages")
    public void sendHelloMsg(){
        for (int i = 1; i<=50; i++){
            fanoutSender.send("这是我发送的第"+i+"个消息");
        }
    }
}
