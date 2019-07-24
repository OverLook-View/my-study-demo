package com.example.test.rabbitmq.sbrabbitmqdemo.rabbitmq.recerver;

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
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello){
        System.out.println("Receiver: "+hello);
    }
}
