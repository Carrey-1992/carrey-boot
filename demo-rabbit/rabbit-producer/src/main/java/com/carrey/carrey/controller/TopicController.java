package com.carrey.carrey.controller;

import com.carrey.carrey.config.TopicConfig;
import com.carrey.carrey.rabbitMq.TopicProvider1;
import com.carrey.carrey.rabbitMq.TopicProvider2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("topic")
public class TopicController {

    @Autowired
    private TopicProvider1 topicProvider1;

    @Autowired
    private TopicProvider2 topicProvider2;

    @GetMapping("send/messages1")
    public void sendWorkMsg1() {
        topicProvider1.sendMessage("该信息来自"+ TopicConfig.MAIN_QUEUE +"队列");
    }

    @GetMapping("send/messages2")
    public void sendWorkMsg2() {
            topicProvider2.sendMessage("该信息来自"+TopicConfig.WOMAN_QUEUE+"队列");
    }
}
