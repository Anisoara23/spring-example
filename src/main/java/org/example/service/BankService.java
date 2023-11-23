package org.example.service;

import org.example.entity.Bank;

import java.util.List;
import java.util.Map;

public interface BankService {

    List<Map<String, String>> getBankCodes();

    Bank getBankByCode(String code);
}
