package com.cb.service.impl;

import com.cb.Messages;
import com.cb.service.NotificationService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.cb.commons.notification.rq.NotificationRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration templateConfig;

    @Autowired
    private Messages messages;

    @Value("${mail.from.email}")
    private String fromEmail;

    @Value("${mail.from.name}") 
    private String fromName;

    @Override
    public void sendNotification(NotificationRq rq) throws MessagingException, IOException, TemplateException {
        rq.getTo().forEach(toEmails -> {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                helper.setFrom(fromEmail);
                helper.setTo("dhruv.rbs.java@gmail.com");

                helper.setSubject("Test Template Email");

                String templateName = messages.getEmailProperty("email.signup.template.name");
                Template template = null;
                template = templateConfig.getTemplate(templateName);
                Map<String, Object> model = new ModelMap();
                model.put("username", toEmails.getUserId());
                model.put("action_url", "#");
                model.put("login_page", "#");
                model.put("sender_name", "#");
                String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
                helper.setText(html, true);
                mailSender.send(message);
            } catch (MessagingException | IOException | TemplateException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
