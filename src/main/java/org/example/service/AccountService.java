package org.example.service;

import org.example.entity.Account;
import org.example.pojo.CustomerFinancialProfile;

import java.util.List;

public interface AccountService extends CommonService<Account, String> {

    List<CustomerFinancialProfile> getCustomersWithAccountIds();
}
