package org.example.batch;

import org.example.entity.Address;
import org.example.entity.Bank;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class BankFieldSetMapper implements FieldSetMapper<Bank> {

    @Override
    public Bank mapFieldSet(FieldSet fieldSet) throws BindException {
        Bank bank = new Bank();
        Address address = new Address();
        bank.setCode(fieldSet.readString("code"));
        bank.setName(fieldSet.readString("name"));
        address.setCountry(fieldSet.readString("country"));
        address.setCity(fieldSet.readString("city"));
        address.setStreet(fieldSet.readString("street"));
        address.setPostalCode(fieldSet.readString("postal_code"));
        bank.setAddress(address);
        return bank;
    }
}
