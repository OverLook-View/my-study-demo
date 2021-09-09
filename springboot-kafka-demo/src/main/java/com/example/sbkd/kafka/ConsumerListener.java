package com.example.sbkd.kafka;

import com.example.sbkd.SpringbootKafkaDemoApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @description:
 * @author: OverlookView
 * @create: 2020-05-27 15:05
 **/
@Component
public class ConsumerListener {
    private static final String groupId= UUID.randomUUID().toString();

    //监听主题
    @KafkaListener(topics = "testTopic")
    public void onMessage(String message){
        System.out.println("testTopic1: "+message);
    }

    @KafkaListener(topics = "testTopic2")
    public void onMessage2(String message){
        System.out.println("testTopic2: "+message);
    }

    //多分组成订阅模式
    @KafkaListener(topics = "testTopic",groupId = "test2")
    public void onMessage3(String message){
        System.out.println("testTopic1-2: "+message);
    }
}
