package com.cb.notification.service;

import jakarta.mail.MessagingException;
import org.cb.commons.email.rq.EmailRq;

import java.io.UnsupportedEncodingException;

public interface IEmailService {

    public void sendHtmlEmail(final EmailRq rq) throws MessagingException,
                    UnsupportedEncodingException;

}
