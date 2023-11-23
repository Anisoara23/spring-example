package org.example.service.impl;

import org.example.dao.BankDao;
import org.example.entity.Bank;
import org.example.service.BankService;

import java.util.List;
import java.util.Map;

public class BankServiceImpl implements BankService {

    private final BankDao bankDao;

    public BankServiceImpl(BankDao bankDao) {
        this.bankDao = bankDao;
    }

    @Override
    public List<Map<String, String>> getBankCodes() {
        List<Map<String, String>> bankCodes = bankDao.getBankCodes();

        if (bankCodes.isEmpty()) {
            throw new IllegalStateException("No banks in system");
        }

        return bankCodes;
    }

    @Override
    public Bank getBankByCode(String code) {
        return bankDao.getBankByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Incorrect bank code!"));
    }
}
