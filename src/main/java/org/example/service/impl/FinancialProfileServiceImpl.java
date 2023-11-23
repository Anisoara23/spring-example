package org.example.service.impl;

import org.example.dao.FinancialProfileDao;
import org.example.service.FinancialProfileService;

import java.math.BigDecimal;

public class FinancialProfileServiceImpl implements FinancialProfileService {

    private final FinancialProfileDao financialProfileDao;

    public FinancialProfileServiceImpl(FinancialProfileDao financialProfileDao) {
        this.financialProfileDao = financialProfileDao;
    }

    @Override
    public boolean existsFinancialProfileById(String id) {
        return financialProfileDao.existsFinancialProfileById(id);
    }

    @Override
    public BigDecimal getLoanAmount(String id) {
        return financialProfileDao.getLoanAmount(id);
    }

    @Override
    public void updateAmount(String id, BigDecimal newAmount) {
        financialProfileDao.updateAmount(id, newAmount);
    }
}
