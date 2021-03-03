package com.carrey.carrey.rabbit;

import com.carrey.carrey.config.DirectConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = DirectConfig.QUEUE_DIRECT)
public class DirectReceiver implements ChannelAwareMessageListener {

    /**
     * 用于处理收到的Rabbit消息的回调。
     *
     * <p>消费者收到消息后，手动调用basic.ack/basic.nack/basic.reject后，RabbitMQ收到这些消息后，才认为本次投递成功。
     * basic.ack用于肯定确认 
     * basic.nack用于否定确认（注意：这是AMQP 0-9-1的RabbitMQ扩展） 
     * basic.reject用于否定确认，但与basic.nack相比有一个限制:一次只能拒绝单条消息 
     * 消费者端以上的3个方法都表示消息已经被正确投递，但是basic.ack表示消息已经被正确处理，
     * 但是basic.nack,basic.reject表示没有被正确处理，但是RabbitMQ中仍然需要删除这条消息。 <p/>
     * @param channel
     * @throws Exception
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            String msg = message.toString();
            String[] msgArray = msg.split("'");//可以点进Message里面看源码,单引号直接的数据就是我们的消息数据
            String str = msgArray[1].trim();
            log.info("DirectReceiver接收处理队列Work当中的消息：[{}]",str);
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            //为true会重新放回队列
            channel.basicReject(deliveryTag, false);
            log.error("DirectReceiver消费消息失败！",e);
        }

    }

}
