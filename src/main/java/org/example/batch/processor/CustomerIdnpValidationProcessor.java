package org.example.batch.processor;

import org.example.entity.Customer;
import org.springframework.batch.item.ItemProcessor;

public class CustomerIdnpValidationProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer customer) throws Exception {
        if (customer.getIdnp().length() != 13) {
            return null;
        }

        return customer;
    }
}
