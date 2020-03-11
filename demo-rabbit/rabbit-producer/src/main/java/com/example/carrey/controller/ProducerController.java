package com.example.carrey.controller;

import com.example.carrey.rabbitMq.HelloProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class ProducerController {

  @Autowired
  private HelloProvider msgProducer;


  @GetMapping("send/messages")
  public void sendHelloMsg(){
    for (int i = 1; i<=50; i++){
      msgProducer.sendMsg("这是我发送的第"+i+"个消息");
    }
  }
}
