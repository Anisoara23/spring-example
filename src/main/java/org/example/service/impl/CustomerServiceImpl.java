package org.example.service.impl;

import org.example.dao.CustomerDao;
import org.example.entity.Customer;
import org.example.service.CustomerService;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void add(Customer customer) {
        customerDao.addCustomer(customer);
    }

    @Override
    public void remove(Integer integer) {

    }

    @Override
    public List<String> getEmails() {
        return customerDao.getEmails();
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerDao.getCustomerByEmail(email);
    }
}
