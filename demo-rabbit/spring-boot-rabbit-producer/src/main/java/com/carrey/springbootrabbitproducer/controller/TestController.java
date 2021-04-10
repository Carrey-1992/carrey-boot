package com.carrey.springbootrabbitproducer.controller;

import com.carrey.springbootrabbitproducer.mq.CarreyMsgSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author Conway
 * @className TestController
 * @description
 * @date 2021/1/24 下午3:32
 */
@RequestMapping("test/")
@RestController
public class TestController {

    @Autowired
    private CarreyMsgSender sender;

    @PostMapping("msg1")
    public void sendMsg1() throws Exception {
        sender.sendMsg1("msg1",new HashMap<String,Object>());
    }

    @PostMapping("msg2")
    public void sendMsg2() throws Exception {
        sender.sendMsg2("msg2",new HashMap<String,Object>());
    }

    @PostMapping("msg3")
    public void sendMsg3() throws Exception {
        sender.sendMsg3("msg3",new HashMap<String,Object>());
    }
}
