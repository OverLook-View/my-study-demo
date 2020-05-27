package com.example.sbkd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
class SpringbootKafkaDemoApplicationTests {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Test
    void contextLoads() {
        //发送测试消息
        kafkaTemplate.send("testTopic","测试消息2");
    }

}
