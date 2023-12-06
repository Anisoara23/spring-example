package org.example.batch.preparedstatement;

import org.example.entity.Bank;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankPreparedStatementSetter implements ItemPreparedStatementSetter<Bank> {

    @Override
    public void setValues(Bank bank, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, bank.getCode());
        preparedStatement.setString(2, bank.getName());
        preparedStatement.setString(3, bank.getAddress().getCountry());
        preparedStatement.setString(4, bank.getAddress().getCity());
        preparedStatement.setString(5, bank.getAddress().getStreet());
        preparedStatement.setString(6, bank.getAddress().getPostalCode());
    }
}