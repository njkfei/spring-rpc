package com.njp.learn.serviceImpl;

import com.njp.learn.model.Account;
import com.njp.learn.service.AccountService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niejinping on 2016/7/30.
 */
public class AccountServiceImpl implements AccountService {
    private List<Account> accounts = new ArrayList<>();

    @Override
    public void insertAccount(Account account) {
        accounts.add(account);
    }

    @Override
    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public long cancelAccount(long accountId) {
   //     System.out.println("service account id : " + accountId);

        return accountId;
    }
}
