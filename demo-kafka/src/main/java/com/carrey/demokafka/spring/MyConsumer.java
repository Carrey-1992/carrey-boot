package com.carrey.demokafka.spring;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Conway
 * @className MyConsumer
 * @description
 * @date 2021/2/22 6:31 下午
 */
@Component
public class MyConsumer {

    /**
     *@KafkaListener(groupId="testGroup",topicPartitions={
     *             @TopicPartition(topic="topic1",partitions={"0","1"}),
     *             @TopicPartition(topic="topic2",partitions="0",partitionOffsets=@PartitionOffset(partition="1",initialOffset="100"))})
     */
    @KafkaListener(topics = "mytopic", groupId = "testGroup")
    public void listen(ConsumerRecord<String, String> record) {
        String value = record.value();
        System.out.println("值："+value);
        System.out.println(record);
    }

}
