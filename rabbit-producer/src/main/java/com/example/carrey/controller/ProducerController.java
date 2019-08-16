package com.example.carrey.controller;

import com.example.carrey.rabbitMq.MsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

  @Autowired
  private MsgProducer msgProducer;

  @GetMapping("send/messages")
  public void sendMsg(){
    for (int i = 1; i<=50; i++){
      msgProducer.sendMsg("这是我发送的第"+i+"个消息");
    }
  }
}
