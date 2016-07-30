package com.njp.learn.service;

import com.njp.learn.model.Account;

import java.util.List;

/**
 * Created by niejinping on 2016/7/30.
 */
public interface AccountService {
    void insertAccount(Account account);
    List<Account> getAccounts();

    long cancelAccount(long accountId);
}
