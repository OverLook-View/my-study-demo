package com.example.sbkd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@SpringBootTest
class SpringbootKafkaDemoApplicationTests {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Test
    void contextLoads() {
        //发送测试消息
        ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send("testTopic", "测试消息5");
        send.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("=============================发送消息失败");
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                System.out.println("=============================发送消息成功");
            }
        });
    }

}
