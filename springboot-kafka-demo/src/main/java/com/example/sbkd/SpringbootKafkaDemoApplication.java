package com.example.sbkd;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

@SpringBootApplication
public class SpringbootKafkaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootKafkaDemoApplication.class, args);
    }
}
