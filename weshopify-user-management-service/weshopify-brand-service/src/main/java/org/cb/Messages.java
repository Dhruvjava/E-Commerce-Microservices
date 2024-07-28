package org.cb;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Messages {

    @Autowired
    private MessageSource errorSource;

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor errorSourceAccessor;

    private MessageSourceAccessor messageSourceAccessor;

    @PostConstruct
    public void init() {
        errorSourceAccessor = new MessageSourceAccessor(errorSource, Locale.ENGLISH);
        messageSourceAccessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }

    public String getErrorProperty(String code) {
        return errorSourceAccessor.getMessage(code);
    }

    public String getMessageProperty(String code) {
        return messageSourceAccessor.getMessage(code);
    }

}
