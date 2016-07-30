package com.njp.learn.config;

import com.njp.learn.service.CheckingAccountService;
import com.njp.learn.serviceImpl.SimpleCheckingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.remoting.JmsInvokerServiceExporter;

/**
 * Created by niejinping on 2016/7/30.
 */
@Configuration
@Import(JmsConfig.class)
public class ServerConfig {
    @Autowired
    JmsConfig jmsConfig;

    @Bean
    public CheckingAccountService checkingAccountService(){
        return new SimpleCheckingAccountService();
    }

    @Bean
    public JmsInvokerServiceExporter jmsInvokerServiceExporter(){
        JmsInvokerServiceExporter jmsInvokerServiceExporter = new JmsInvokerServiceExporter();
        jmsInvokerServiceExporter.setServiceInterface(CheckingAccountService.class);
        jmsInvokerServiceExporter.setService(checkingAccountService());

        return jmsInvokerServiceExporter;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(jmsConfig.connectionFactory());
        simpleMessageListenerContainer.setDestination(jmsConfig.queue());
        simpleMessageListenerContainer.setConcurrentConsumers(32);
        simpleMessageListenerContainer.setMessageListener(jmsInvokerServiceExporter());

        return simpleMessageListenerContainer;
    }


}
