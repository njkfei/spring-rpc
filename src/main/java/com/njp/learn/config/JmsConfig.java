package com.njp.learn.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;

/**
 * Created by niejinping on 2016/7/30.
 */
public class JmsConfig {
    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61616");
        return  connectionFactory;
    }

    @Bean
    public ActiveMQQueue queue(){
        ActiveMQQueue queue = new ActiveMQQueue("mm");
        return  queue;
    }
}
