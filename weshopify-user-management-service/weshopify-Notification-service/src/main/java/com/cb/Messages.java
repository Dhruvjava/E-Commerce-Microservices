package com.cb;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Messages {

    @Autowired
    @Qualifier("errorProperties")
    private MessageSource errorProperties; 

    @Autowired
    @Qualifier("messageProperties")
    private MessageSource messageProperties;

//    @Autowired
//    @Qualifier("reportProperties")
//    private MessageSource reportProperties;
//
    @Autowired
    @Qualifier("emailProperties")
    private MessageSource emailProperties;

    private MessageSourceAccessor errorPropertiesAccessor;

    private MessageSourceAccessor messagePropertiesAccessor;

//    private MessageSourceAccessor reportPropertiesAccessor;
//
    private MessageSourceAccessor emailPropertiesAccessor;

    @PostConstruct
    private void init() {
        errorPropertiesAccessor = new MessageSourceAccessor(errorProperties, Locale.ENGLISH);
        messagePropertiesAccessor = new MessageSourceAccessor(messageProperties, Locale.ENGLISH);
//        reportPropertiesAccessor = new MessageSourceAccessor(reportProperties, Locale.ENGLISH);
        emailPropertiesAccessor = new MessageSourceAccessor(emailProperties, Locale.ENGLISH);
    }

    public String getErrorProperty(String code) {
        return errorPropertiesAccessor.getMessage(code);
    }

    public String getMessageProperty(String code) {
        return messagePropertiesAccessor.getMessage(code);
    }

//    public String getReportProperty(String code) {
//        return reportPropertiesAccessor.getMessage(code);
//    }
//
    public String getEmailProperty(String code) {
        return emailPropertiesAccessor.getMessage(code);
    }

}
