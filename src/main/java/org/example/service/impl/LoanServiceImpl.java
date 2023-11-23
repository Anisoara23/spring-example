package org.example.service.impl;

import org.example.dao.LoanDao;
import org.example.entity.Loan;
import org.example.pojo.CustomerFinancialProfile;
import org.example.service.FinancialProfileService;
import org.example.service.LoanService;

import java.util.List;

public class LoanServiceImpl implements LoanService {

    private final LoanDao loanDao;

    private final FinancialProfileService financialProfileService;

    public LoanServiceImpl(LoanDao loanDao, FinancialProfileService financialProfileService) {
        this.loanDao = loanDao;
        this.financialProfileService = financialProfileService;
    }

    @Override
    public void add(Loan loan) {
        loanDao.addLoan(loan);
    }

    @Override
    public void remove(String id) {
        if (!financialProfileService.existsFinancialProfileById(id)) {
            throw new IllegalArgumentException("Loan with id = %s does not exist!".formatted(id));
        }

        loanDao.removeLoanById(id);
    }

    @Override
    public List<CustomerFinancialProfile> getCustomersWithLoansIds() {
        List<CustomerFinancialProfile> customersWithLoansIds = loanDao.getCustomersWithLoansIds();

        if (customersWithLoansIds.isEmpty()) {
            throw new IllegalStateException("No loans in the system!");
        }

        return customersWithLoansIds;
    }
}
