package com.cb.service;

import com.cb.rq.NotificationRq;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface NotificationService {

    public void sendNotification(NotificationRq rq) throws MessagingException, IOException, TemplateException;

}
