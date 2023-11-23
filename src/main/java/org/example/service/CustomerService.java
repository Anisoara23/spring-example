package org.example.service;

import org.example.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService extends CommonService<Customer, Integer> {

    List<String> getEmails();

    Optional<Customer> getCustomerByEmail(String email);
}
