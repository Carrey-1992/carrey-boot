package com.example.carrey.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Conway
 * @className RabbitmqConsumer
 * @description
 * @date 2021/1/21 下午3:17
 */
public class RabbitmqConsumer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 2.设置连接工厂的属性
        connectionFactory.setHost("123.57.34.196");
        connectionFactory.setPort(5672);
        //虚拟主机
        connectionFactory.setVirtualHost("carrey");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        // 3.通过连接工厂创建连接对象
        Connection connection = connectionFactory.newConnection();

        // 4.通过连接创建channel
        Channel channel = connection.createChannel();

        //声明队列
        String queueName = "carrey-queue-01";
        /**
         * queue:队列的名称
         * durable:是否持久化, 队列的声明默认是存放到内存中的，如果rabbitmq重启会丢失，如果想重启之后还存在就要使队列持久化，
         * 保存到Erlang自带的Mnesia数据库中，当rabbitmq重启之后会读取该数据库
         * exclusive:当连接关闭时connection.close()该队列是否会自动删除；
         * 二：该队列是否是私有的private，如果不是排外的，可以使用两个消费者都访问同一个队列，
         * 没有任何问题，如果是排外的，会对当前队列加锁，其他通道channel是不能访问的，如果强制访问会报异常
         * com.rabbitmq.client.ShutdownSignalException: channel error; protocol method: #method<channel.close>(reply-code=405, reply-text=RESOURCE_LOCKED - cannot obtain exclusive access to locked queue 'queue_name' in vhost '/', class-id=50, method-id=20)
         * 一般等于true的话用于一个队列只能有一个消费者来消费的场景
         * autodelete:是否自动删除，当最后一个消费者断开连接之后队列是否自动被删除，可以通过RabbitMQ Management，
         * 查看某个队列的消费者数量，当consumers = 0时队列就会自动删除
         */
        channel.queueDeclare(queueName,true,false,true,null);

        //创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        //推模式，自动确认
        channel.basicConsume(queueName,true,queueingConsumer);

        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String reserveMsg = new String(delivery.getBody());
            System.out.println("消费消息：" + reserveMsg);
        }
    }

}
