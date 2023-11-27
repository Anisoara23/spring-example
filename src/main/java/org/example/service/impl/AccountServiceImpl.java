package org.example.service.impl;

import org.example.dao.AccountDao;
import org.example.entity.Account;
import org.example.pojo.CustomerFinancialProfile;
import org.example.service.AccountService;
import org.example.service.FinancialProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    private final FinancialProfileService financialProfileService;

    public AccountServiceImpl(AccountDao accountDao, FinancialProfileService financialProfileService) {
        this.accountDao = accountDao;
        this.financialProfileService = financialProfileService;
    }

    @Override
    public void add(Account account) {
        accountDao.addAccount(account);
    }

    @Override
    public void remove(String id) {
        if (!financialProfileService.existsFinancialProfileById(id)) {
            throw new IllegalArgumentException("Account with id = %s does not exist!".formatted(id));
        }

        accountDao.removeAccountById(id);
    }

    @Override
    public List<CustomerFinancialProfile> getCustomersWithAccountIds() {
        List<CustomerFinancialProfile> customersWithAccountIds = accountDao.getCustomersWithAccountIds();

        if (customersWithAccountIds.isEmpty()) {
            throw new IllegalStateException("No accounts in the system!");
        }

        return customersWithAccountIds;
    }
}
