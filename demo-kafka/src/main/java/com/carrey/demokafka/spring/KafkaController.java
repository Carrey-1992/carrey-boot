package com.carrey.demokafka.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Conway
 * @className KafkaController
 * @description
 * @date 2021/2/22 6:23 下午
 */
@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping("/send")
    public void send() {
        kafkaTemplate.send("mytopic",0,"key","this is a msg");
    }

}
