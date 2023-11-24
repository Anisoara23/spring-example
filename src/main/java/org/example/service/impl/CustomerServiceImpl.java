package org.example.service.impl;

import org.example.dao.CustomerDao;
import org.example.entity.Customer;
import org.example.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void add(Customer customer) {
        if (customerDao.getCustomerByEmail(customer.getEmail()).isPresent()){
            return;
        }
        customerDao.addCustomer(customer);
    }

    @Override
    public void remove(Integer integer) {

    }

    @Override
    public List<String> getEmails() {
        List<String> emails = customerDao.getEmails();

        if (emails.isEmpty()) {
            throw new IllegalStateException("No customers in the system!");
        }

        return emails;
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerDao.getCustomerByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Incorrect email"));
    }
}
