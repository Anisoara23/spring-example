package org.example.service;

import java.math.BigDecimal;

public interface FinancialProfileService {

    boolean existsFinancialProfileById(String id);

    BigDecimal getLoanAmount(String id);

    void updateAmount(String id, BigDecimal newAmount);
}
