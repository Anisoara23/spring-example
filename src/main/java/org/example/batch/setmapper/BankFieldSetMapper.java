package org.example.batch.setmapper;

import org.example.dto.BankDto;
import org.example.entity.Address;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class BankFieldSetMapper implements FieldSetMapper<BankDto> {

    @Override
    public BankDto mapFieldSet(FieldSet fieldSet) throws BindException {
        Address address = new Address();
        address.setCountry(fieldSet.readString("country"));
        address.setCity(fieldSet.readString("city"));
        address.setStreet(fieldSet.readString("street"));
        address.setPostalCode(fieldSet.readString("postal_code"));

        BankDto bankDto = new BankDto();
        bankDto.setCode(fieldSet.readString("code"));
        bankDto.setName(fieldSet.readString("name"));
        bankDto.setAddress(address);

        return bankDto;
    }
}
