package com.example.carrey.controller;

import com.example.carrey.config.DirectConfig;
import com.example.carrey.rabbitMq.DirectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("direct")
public class DirectController {

    @Autowired
    private DirectProvider directProvider;

    @GetMapping("send/messages")
    public void send() {
        directProvider.sendMessage("该信息来自"+ DirectConfig.QUEUE_DIRECT +"队列");
    }
}
