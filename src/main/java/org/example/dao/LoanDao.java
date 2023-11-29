package org.example.dao;

import org.example.entity.Loan;
import org.example.pojo.CustomerFinancialProfile;

import java.util.List;
import java.util.Optional;

public interface LoanDao {

    String addLoan(Loan loan);

    List<CustomerFinancialProfile> getCustomersWithLoansIds();

    void removeLoanById(String id);
}
