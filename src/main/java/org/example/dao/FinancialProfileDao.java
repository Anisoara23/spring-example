package org.example.dao;

import java.math.BigDecimal;

public interface FinancialProfileDao {

    BigDecimal getAmount(String id);

    int updateAmount(String id, BigDecimal newAmount);

    boolean existsFinancialProfileById(String id);
}
