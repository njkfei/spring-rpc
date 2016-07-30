package com.njp.learn.config;

import com.njp.learn.model.Account;
import com.njp.learn.service.AccountService;
import com.njp.learn.serviceImpl.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * Created by niejinping on 2016/7/30.
 */
@Configuration
public class RmiBean {
    @Bean
    public AccountService accountService(){
        return new AccountServiceImpl();
    }

    /**
     */
    @Bean
    public RmiServiceExporter rmiServiceExporter(){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();

        rmiServiceExporter.setServiceName("AccountService");
        rmiServiceExporter.setService(accountService());
        rmiServiceExporter.setServiceInterface(AccountService.class);
        rmiServiceExporter.setRegistryPort(1199);

        return rmiServiceExporter;
    }
}
