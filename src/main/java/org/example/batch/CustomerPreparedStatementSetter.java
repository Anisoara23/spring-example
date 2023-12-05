package org.example.batch;

import org.example.entity.Customer;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerPreparedStatementSetter implements ItemPreparedStatementSetter<Customer> {


    @Override
    public void setValues(Customer customer, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, customer.getFirstName());
        preparedStatement.setString(2, customer.getLastName());
        preparedStatement.setDate(3, new Date(customer.getBirthDate().getTime()));
        preparedStatement.setString(4, customer.getIdnp());
        preparedStatement.setString(5, customer.getEmail());
        preparedStatement.setString(6, customer.getPhoneNumber());
    }
}