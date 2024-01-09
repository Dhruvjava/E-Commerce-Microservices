package com.cb.service;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import org.cb.commons.notification.rq.NotificationRq;

import java.io.IOException;

public interface NotificationService {

    public void sendNotification(NotificationRq rq) throws MessagingException, IOException, TemplateException;

}
