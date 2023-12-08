package org.example.batch.processor;

import org.example.dto.BankDto;
import org.example.entity.Bank;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class BankDtoToBankEntityProcessor implements ItemProcessor<BankDto, Bank> {

    @Override
    public Bank process(BankDto bankDto) throws Exception {
        Bank bank = new Bank();
        bank.setCode(bankDto.getCode());
        bank.setName(bankDto.getName());
        bank.setAddress(bankDto.getAddress());
        bank.setRegisteredAt(LocalDate.now());
        return bank;
    }
}
