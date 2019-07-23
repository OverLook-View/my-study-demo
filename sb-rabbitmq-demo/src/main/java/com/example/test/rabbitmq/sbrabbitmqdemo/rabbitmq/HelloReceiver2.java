package com.example.test.rabbitmq.sbrabbitmqdemo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: OverlookView
 * @create: 2019-07-08 11:24
 **/
@Component
@RabbitListener(queues = "q_hello")
public class HelloReceiver2 {

    @RabbitHandler
    public void process(String hello){
        System.out.println("Receiver2: "+hello);
    }
}
