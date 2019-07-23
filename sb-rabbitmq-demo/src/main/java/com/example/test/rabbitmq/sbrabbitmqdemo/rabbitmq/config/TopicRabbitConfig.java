package com.example.test.rabbitmq.sbrabbitmqdemo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: OverlookView
 * @create: 2019-07-09 10:12
 **/
@Configuration
public class TopicRabbitConfig {
    final static String message="q_topic_message";
    final static String messages="q_topic_messages";

    @Bean
    public Queue queueMessage(){
        return new Queue(TopicRabbitConfig.message);
    }
    @Bean
    public Queue queueMessages(){
        return new Queue(TopicRabbitConfig.messages);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("mybootexchange");
    }
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage,TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages,TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}
