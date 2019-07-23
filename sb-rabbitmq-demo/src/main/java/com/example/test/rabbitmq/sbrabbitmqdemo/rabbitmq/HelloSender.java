package com.example.test.rabbitmq.sbrabbitmqdemo.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: OverlookView
 * @create: 2019-07-08 10:30
 **/
@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String context){
//        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        String context="Hello "+dateStr;
        System.out.println(context);
        this.rabbitTemplate.convertAndSend("q_hello",context);
    }
}
