package com.carrey.carrey.controller;

import com.carrey.carrey.rabbitMq.WorkProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("work")
public class WorkController {

    @Autowired
    private WorkProvider workSender;

    @GetMapping("send/messages")
    public void sendWorkMsg() {
        for (int i = 1; i<=50; i++){
            workSender.sendMessage("这是work发送的第"+i+"个消息");
        }
    }

}
