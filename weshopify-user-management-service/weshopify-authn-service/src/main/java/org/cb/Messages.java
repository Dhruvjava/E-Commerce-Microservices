package org.cb;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class Messages {

    private final MessageSource errorSource;

    private final MessageSource messageSource;

    private MessageSourceAccessor errorSourceAccessor;

    private MessageSourceAccessor messageSourceAccessor;

    @PostConstruct
    public void init() {
        errorSourceAccessor = new MessageSourceAccessor(errorSource, Locale.ENGLISH);
        messageSourceAccessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }

    public String getErrorProperties(String code) {
        return errorSourceAccessor.getMessage(code);
    }

    public String getMessageProperties(String code) {
        return messageSourceAccessor.getMessage(code);
    }

}
