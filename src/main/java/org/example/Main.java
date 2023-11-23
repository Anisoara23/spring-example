package org.example;

import org.example.ui.UserInterface;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("configuration/config.xml");

        UserInterface userInterface = context.getBean(UserInterface.class);
        userInterface.displayUserInterface();
    }
}