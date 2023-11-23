package org.example.service.impl;

import org.example.dao.AccountDao;
import org.example.entity.Account;
import org.example.pojo.CustomerFinancialProfile;
import org.example.service.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void add(Account account) {
        accountDao.addAccount(account);
    }

    @Override
    public void remove(String id) {
        accountDao.removeAccountById(id);
    }

    @Override
    public List<CustomerFinancialProfile> getCustomersWithAccountIds() {
        return accountDao.getCustomersWithAccountIds();
    }
}
