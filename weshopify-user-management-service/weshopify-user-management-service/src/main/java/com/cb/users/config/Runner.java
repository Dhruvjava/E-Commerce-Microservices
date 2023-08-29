package com.cb.users.config;

import com.cb.users.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {


    @Autowired
    private Messages messages;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started !!");
        System.out.println(messages.getErrorProperty("EC_PROBLEM_IN_SAVEING_PERMISSIONS"));
        System.out.println("Finished !!");
    }
}
