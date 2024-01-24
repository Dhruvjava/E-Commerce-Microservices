package com.cb.notification.service.impl;

import com.cb.Messages;
import com.cb.base.data.rs.BaseDataRs;
import com.cb.base.rs.ErrorRs;
import com.cb.constants.ErrorCodes;
import com.cb.constants.MessageCodes;
import com.cb.exception.NotificationException;
import com.cb.notification.helper.NotificationHelper;
import com.cb.notification.service.IEmailService;
import com.cb.notification.service.INotificationService;
import com.cb.util.Utils;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.cb.commons.email.rq.EmailRq;
import org.cb.commons.email.rq.NotificationRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class NotificationServiceImpl implements INotificationService {

    @Autowired
    private IEmailService emailService;

    @Autowired
    private Configuration templateConfig;

    @Autowired
    private Messages messages;

    @Value("${user.verify.url}")
    private String ACTION_URL;

    @Value("${user.login.url}")
    private String LOGIN_URL;


    @Override
    public BaseDataRs sendNotification(NotificationRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing sendNotification(NotificationRq) -> ");
        }
        try {
            List<ErrorRs> errors = NotificationHelper.validateSendNotification(rq, messages);
            Optional.ofNullable(errors).filter(Utils::isNotEmpty).ifPresent(err -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
                throw new NotificationException(ErrorCodes.EC_INVALID_INPUT, errorMessage, err);
            });
            Map<String, Object> model = new HashMap<>();
            model.put("username", rq.getUsername());
            model.put("action_url", ACTION_URL);
            model.put("login_page", LOGIN_URL);
            String html = NotificationHelper.prepareVerifyEmailTempate(templateConfig, messages,
                            model);
            Optional.ofNullable(html).filter(Utils::isEmpty).ifPresentOrElse(htm -> {
                EmailRq emailRq = new EmailRq();
                emailRq.setSubject(rq.getSubject());
                emailRq.setToEmailAddr(rq.getTo());
                emailRq.setMessageText(html);
                try {
                    emailService.sendHtmlEmail(emailRq);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }, () -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_EMAIL_BODY_NOT_FOUND);
                throw new NotificationException(ErrorCodes.EC_EMAIL_BODY_NOT_FOUND, errorMessage);

            });

            String message = messages.getMessageProperty(MessageCodes.MC_EMAIL_SENT_SUCCESSFULLY);
            return new BaseDataRs(message);
        } catch (Exception e) {
            try {
                throw e;
            } catch (TemplateException ex) {
                throw new RuntimeException(ex);
            } catch (IOException  ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
