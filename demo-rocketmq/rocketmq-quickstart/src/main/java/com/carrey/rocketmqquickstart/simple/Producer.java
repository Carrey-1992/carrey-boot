/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.carrey.rocketmqquickstart.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;

/**
 * This class demonstrates how to send messages to brokers using provided {@link DefaultMQProducer}.
 */
public class Producer {
    public static void main(String[] args) throws Exception {

        /*
         * Instantiate with a producer group name.
         */
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");

        /*
         * Specify name server addresses.
         * <p/>
         *
         * Alternatively, you may specify name server addresses via exporting environmental variable: NAMESRV_ADDR
         * <pre>
         * {@code
         * producer.setNamesrvAddr("name-server1-ip:9876;name-server2-ip:9876");
         * }
         * </pre>
         */
        producer.setNamesrvAddr("172.16.20.3:9876");
        /*
         * Launch the instance.
         */
        producer.start();
        // 发送单条消息
        Message msg = new Message("TOPIC_TEST", "hello rocketmq".getBytes());
        SendResult sendResult = null;
        sendResult = producer.send(msg);
        // 输出结果
        System.out.printf("%s%n",sendResult);

        //发送带key的消息
        msg = new Message("TOPIC_TEST",null,"ODS2020072615490001","{\"id\":1, \"orderNo\":\"ODS2020072615490001\",\"buyerId\":1,\"sellerId\":1  }".getBytes());
        sendResult = producer.send(msg);
        // 输出结果
        System.out.printf("%s%n",sendResult);

        // 批量发送
        ArrayList<Message> msgs = new ArrayList<>();
        msgs.add( new Message("TOPIC_TEST", null, "ODS2020072615490002", "{\"id\":2, \"orderNo\":\"ODS2020072615490002\",\"buyerId\":1,\"sellerId\":3  }".getBytes()) );
        msgs.add( new Message("TOPIC_TEST", null, "ODS2020072615490003", "{\"id\":4, \"orderNo\":\"ODS2020072615490003\",\"buyerId\":2,\"sellerId\":4  }".getBytes()) );
        sendResult = producer.send(msgs);
        System.out.printf("%s%n", sendResult);


//        for (int i = 0; i < 1000; i++) {
//            try {
//
//                /*
//                 * Create a message instance, specifying topic, tag and message body.
//                 */
//                Message msg = new Message("TopicTest" /* Topic */,
//                    "TagA" /* Tag */,
//                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
//                );
//
//                /*
//                 * Call send message to deliver message to one of brokers.
//                 */
//                SendResult sendResult = producer.send(msg);
//
//                System.out.printf("%s%n", sendResult);
//            } catch (Exception e) {
//                e.printStackTrace();
//                Thread.sleep(1000);
//            }
//        }

        /*
         * Shut down once the producer instance is not longer in use.
         */
        //会删除生产组
        producer.shutdown();
    }
}
