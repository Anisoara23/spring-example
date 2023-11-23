package org.example.service;

import org.example.entity.Bank;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BankService {

    List<Map<String, String>> getBankCodes();

    Optional<Bank> getBankByCode(String code);
}
