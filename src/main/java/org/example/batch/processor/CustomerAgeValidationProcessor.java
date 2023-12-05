package org.example.batch.processor;

import org.example.entity.Customer;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CustomerAgeValidationProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer customer) throws Exception {
        if (getYears(customer.getBirthDate()) < 18) {
            return null;
        }

        return customer;
    }

    private static int getYears(Date date){
        LocalDate currentDate = LocalDate.now();
        LocalDate customerDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return customerDate.until(currentDate).getYears();
    }
}
