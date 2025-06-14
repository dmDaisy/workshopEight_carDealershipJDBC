package com.pluralsight.dealership;

import com.pluralsight.dealership.view.UserInterface;
import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
    public static void main(String[] args) {

        if(args.length < 2){
            System.out.println("Username and/or password is missing");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/car_dealership");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("123");

        UserInterface userInterface = new UserInterface(basicDataSource);
        userInterface.display();
    }
}