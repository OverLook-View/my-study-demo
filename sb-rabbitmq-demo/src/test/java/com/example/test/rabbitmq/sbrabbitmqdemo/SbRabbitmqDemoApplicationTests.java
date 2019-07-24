package com.example.test.rabbitmq.sbrabbitmqdemo;

import com.example.test.rabbitmq.sbrabbitmqdemo.rabbitmq.sender.HelloSender;
import com.example.test.rabbitmq.sbrabbitmqdemo.rabbitmq.sender.TopicMsgSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SbRabbitmqDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private HelloSender helloSender;
    @Autowired
    private TopicMsgSender topicMsgSender;

    @Test
    public void rabbitTest() {
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String context = " Hello " + dateStr;
        for (int i = 0; i < 100; i++) {
            helloSender.send(i + context);
        }
    }

    @Test
    public void topicTest() {
        topicMsgSender.send1();
        topicMsgSender.send2();
    }

}
