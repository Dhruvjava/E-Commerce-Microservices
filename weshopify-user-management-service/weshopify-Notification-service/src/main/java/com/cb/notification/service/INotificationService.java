package com.cb.notification.service;

import com.cb.base.data.rs.BaseDataRs;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import org.cb.commons.email.rq.NotificationRq;

import java.io.IOException;

public interface INotificationService {

    public BaseDataRs sendNotification(NotificationRq rq);

}
