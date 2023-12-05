package org.example.batch;

import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.entity.CustomerInfo;
import org.example.entity.Gender;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class CustomerFieldSetMapper implements FieldSetMapper<Customer> {

    @Override
    public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
        Address address = new Address();
        address.setCountry(fieldSet.readString("country"));
        address.setCity(fieldSet.readString("city"));
        address.setStreet(fieldSet.readString("street"));
        address.setPostalCode(fieldSet.readString("postal_code"));

        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setAddress(address);
        customerInfo.setCitizenship(fieldSet.readString("citizenship"));
        customerInfo.setGender(Gender.valueOf(fieldSet.readString("gender").equals("M")?"MALE":"FEMALE"));
        customerInfo.setOccupation(fieldSet.readString("occupation"));

        Customer customer = new Customer();
        customer.setFirstName(fieldSet.readString("first_name"));
        customer.setLastName(fieldSet.readString("last_name"));
        customer.setEmail(fieldSet.readString("email"));
        customer.setBirthDate(fieldSet.readDate("birth_date"));
        customer.setPhoneNumber(fieldSet.readString("phone_number"));
        customer.setInfo(customerInfo);

        return customer;
    }
}
