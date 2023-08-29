package com.cb.users;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class WeshopifyUserManagementServiceApplication{

    public static void main(String[] args) {
        SpringApplication.run(WeshopifyUserManagementServiceApplication.class, args);
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean(name = "errorProperties")
    public MessageSource errorProperties() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/bundles/application_errors");
        // messageSource.setCacheSeconds(10); //reload messages every 10 seconds
        return messageSource;
    }

    @Bean(name = "messageProperties")
    public MessageSource messageProperties() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/bundles/application_messages");
        // messageSource.setCacheSeconds(10); //reload messages every 10 seconds
        return messageSource;
    }


//    @Bean(name = "reportProperties")
//    public MessageSource reportProperties() {
//        ReloadableResourceBundleMessageSource messageSource =
//                new ReloadableResourceBundleMessageSource();
//        messageSource.setBasenames("classpath:/bundles/report_templates");
//        // messageSource.setCacheSeconds(10); //reload messages every 10 seconds
//        return messageSource;
//    }

//    @Bean(name = "emailProperties")
//    public MessageSource emailProperties() {
//        ReloadableResourceBundleMessageSource messageSource =
//                new ReloadableResourceBundleMessageSource();
//        messageSource.setBasenames("classpath:/bundles/email_templates");
//        // messageSource.setCacheSeconds(10); //reload messages every 10 seconds
//        return messageSource;
//    }

}
