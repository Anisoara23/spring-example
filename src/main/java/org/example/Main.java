package org.example;

import org.example.dao.impl.BankDaoImpl;
import org.example.entity.Loan;
import org.example.entity.LoanType;
import org.example.service.impl.LoanServiceImpl;
import org.example.ui.UserInterface;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("configuration/config.xml");

    }
}