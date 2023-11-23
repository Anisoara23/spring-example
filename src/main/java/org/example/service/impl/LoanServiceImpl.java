package org.example.service.impl;

import org.example.dao.LoanDao;
import org.example.entity.Loan;
import org.example.pojo.CustomerFinancialProfile;
import org.example.service.LoanService;

import java.util.List;

public class LoanServiceImpl implements LoanService {

    private final LoanDao loanDao;

    public LoanServiceImpl(LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    @Override
    public void add(Loan loan) {
        loanDao.addLoan(loan);
    }

    @Override
    public void remove(String id) {
        loanDao.removeLoanById(id);
    }

    @Override
    public List<CustomerFinancialProfile> getCustomersWithLoansIds() {
        return loanDao.getCustomersWithLoansIds();
    }
}
