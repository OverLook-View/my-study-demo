package com.example.test.rabbitmq.sbrabbitmqdemo.rabbitmq.recerver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: OverlookView
 * @create: 2019-07-23 14:46
 **/
@Component
@RabbitListener(queues = "q_topic_message")
public class TopicRecerver1 {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("topicReceiver1: " + hello);
    }
}
