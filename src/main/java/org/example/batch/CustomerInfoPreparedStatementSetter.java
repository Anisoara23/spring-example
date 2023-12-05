package org.example.batch;

import org.example.entity.Customer;
import org.example.entity.CustomerInfo;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerInfoPreparedStatementSetter implements ItemPreparedStatementSetter<Customer> {

    @Override
    public void setValues(Customer customer, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.setString(2, customer.getInfo().getAddress().getCountry());
        preparedStatement.setString(3, customer.getInfo().getAddress().getCity());
        preparedStatement.setString(4, customer.getInfo().getAddress().getStreet());
        preparedStatement.setString(5, customer.getInfo().getAddress().getPostalCode());
        preparedStatement.setString(6, customer.getInfo().getCitizenship());
        preparedStatement.setString(7, customer.getInfo().getGender().toString());
        preparedStatement.setString(8, customer.getInfo().getOccupation());
        preparedStatement.setInt(9, customer.getId());
    }
}