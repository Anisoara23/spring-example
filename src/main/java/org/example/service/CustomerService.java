package org.example.service;

import org.example.entity.Customer;

import java.util.List;

public interface CustomerService extends CommonService<Customer, Integer> {

    List<String> getEmails();

    Customer getCustomerByEmail(String email);
}
