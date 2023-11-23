package org.example;

import org.example.dao.impl.BankDaoImpl;
import org.example.ui.UserInterface;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("start");
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("configuration/config.xml");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames)
                .forEach(System.out::println);
        BankDaoImpl bean = context.getBean(BankDaoImpl.class);
        System.out.println(bean.getBankCodes());
        System.out.println(bean.getBankCodes());
        context.close();
    }
}