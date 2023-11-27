package org.example;

import org.example.config.Config;
import org.example.ui.UserInterface;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        UserInterface userInterface = context.getBean(UserInterface.class);
        userInterface.displayUserInterface();
    }
}