package com.example.test.rabbitmq.sbrabbitmqdemo.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: OverlookView
 * @create: 2019-07-23 14:54
 **/
@Component
public class TopicMsgSender {
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void send1(){
        String context="hi, i am message 1";
        System.out.println("sender: "+context);
        amqpTemplate.convertAndSend("mybootexchange","topic.message",context);
    }
    public void send2(){
        String context="hi, i am message 2";
        System.out.println("sender: "+context);
        amqpTemplate.convertAndSend("mybootexchange","topic.messages",context);
    }
}
