package org.example.batch.rowmapper;

import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.entity.CustomerInfo;
import org.example.entity.Gender;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Address address = new Address();
        address.setCountry(rs.getString("country"));
        address.setCity(rs.getString("city"));
        address.setStreet(rs.getString("street"));
        address.setPostalCode(rs.getString("postal_code"));

        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setAddress(address);
        customerInfo.setId(rs.getInt("id"));
        customerInfo.setCitizenship(rs.getString("citizenship"));
        customerInfo.setOccupation(rs.getString("occupation"));
        customerInfo.setGender(Gender.valueOf(rs.getString("gender")));

        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setEmail(rs.getString("email"));
        customer.setPhoneNumber(rs.getString("phone_number"));
        customer.setBirthDate(rs.getDate("birth_date"));
        customer.setIdnp(rs.getString("idnp"));
        customer.setInfo(customerInfo);
        return customer;
    }
}
