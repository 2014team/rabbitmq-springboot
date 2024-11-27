package com.zzq.rabbitmq.rabbitmqspringboot.comsumer;

import com.rabbitmq.client.Channel;
import com.zzq.rabbitmq.rabbitmqspringboot.config.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zzq
 * @description
 * @date 2022/2/8 21:11
 */
@Component
public class ConsumeListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consume(String msg, Channel channel, Message message) throws IOException {
        System.out.println("队列的消息为：" + msg);
        String correlationId = message.getMessageProperties().getCorrelationId();
        System.out.println("唯一标识为：" + correlationId);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}