package com.njp.learn.serviceImpl;

import com.njp.learn.service.CheckingAccountService;

/**
 * Created by niejinping on 2016/7/30.
 */
public class SimpleCheckingAccountService implements CheckingAccountService {
    @Override
    public long cancelAccount(long accountId) {
    //    System.out.println("service account id : " + accountId);

        return accountId;
    }
}
