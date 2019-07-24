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
@RabbitListener(queues = "q_fanout_B")
public class FanoutReceiverB {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("FanoutReceiverB: " + hello);
    }
}
