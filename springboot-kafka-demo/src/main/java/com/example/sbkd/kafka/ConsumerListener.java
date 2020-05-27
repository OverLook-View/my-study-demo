package com.example.sbkd.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: OverlookView
 * @create: 2020-05-27 15:05
 **/
@Component
public class ConsumerListener {

    //监听主题
    @KafkaListener(topics = "testTopic")
    public void onMessage(String message){
        System.out.println(message);
    }
}
