package org.example.service;

import org.example.entity.Loan;
import org.example.pojo.CustomerFinancialProfile;

import java.util.List;

public interface LoanService extends CommonService<Loan, String> {

    List<CustomerFinancialProfile> getCustomersWithLoansIds();
}
