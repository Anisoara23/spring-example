package org.example.batch;

import org.example.entity.Customer;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerPreparedStatementSetter implements ItemPreparedStatementSetter<Customer> {

    @Override
    public void setValues(Customer customer, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.setString(2, customer.getFirstName());
        preparedStatement.setString(3, customer.getLastName());
        preparedStatement.setDate(4, new Date(customer.getBirthDate().getTime()));
        preparedStatement.setString(5, customer.getIdnp());
        preparedStatement.setString(6, customer.getEmail());
        preparedStatement.setString(7, customer.getPhoneNumber());
    }
}