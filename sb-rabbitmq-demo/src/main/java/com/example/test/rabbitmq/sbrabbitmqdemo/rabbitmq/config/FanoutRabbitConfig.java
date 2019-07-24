package com.example.test.rabbitmq.sbrabbitmqdemo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: OverlookView
 * @create: 2019-07-24 14:16
 **/
@Component
public class FanoutRabbitConfig {
    @Bean
    public Queue aMessage(){
        return new Queue("q_fanout_A");
    }
    @Bean
    public Queue bMessage(){
        return new Queue("q_fanout_B");
    }
    @Bean
    public Queue cMessage(){
        return new Queue("q_fanout_C");
    }
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("mybootfanoutExchange");
    }
    @Bean
    public Binding bindingExchangeA(Queue aMessage,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(aMessage).to(fanoutExchange);
    }
    @Bean
    public Binding bindingExchangeB(Queue bMessage,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(bMessage).to(fanoutExchange);
    }
    @Bean
    public Binding bindingExchangeC(Queue cMessage,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(cMessage).to(fanoutExchange);
    }
}
