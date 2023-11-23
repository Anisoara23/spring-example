package org.example.service.impl;

import org.example.dao.BankDao;
import org.example.entity.Bank;
import org.example.service.BankService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BankServiceImpl implements BankService {

    private final BankDao bankDao;

    public BankServiceImpl(BankDao bankDao) {
        this.bankDao = bankDao;
    }

    @Override
    public List<Map<String, String>> getBankCodes() {
        return bankDao.getBankCodes();
    }

    @Override
    public Optional<Bank> getBankByCode(String code) {
        return bankDao.getBankByCode(code);
    }
}
