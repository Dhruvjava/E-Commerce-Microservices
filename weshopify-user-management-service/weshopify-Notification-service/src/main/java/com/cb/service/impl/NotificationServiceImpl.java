package com.cb.service.impl;

import com.cb.rq.NotificationRq;
import com.cb.service.NotificationService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration templateConfig;

    @Value("${mail.from.email}")
    private String fromEmail;

    @Override
    public void sendNotification(NotificationRq rq) throws MessagingException, IOException, TemplateException {
        if (templateConfig == null) {
            templateConfig = new Configuration(Configuration.VERSION_2_3_31);
            templateConfig.setClassForTemplateLoading(this.getClass(), "/templates");
        }
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(fromEmail);
        helper.setTo("dhruv.rbs.java@gmail.com");
        helper.setSubject("Test Template Email");
        Template template = templateConfig.getTemplate("verify-email.ftl");
//        Map<String, Object> model = new ModelMap();
        Map<String, Object> model = new HashMap<>();
        model.put("username", "Dhruv Kumar");
        model.put("verificationUrl", "Dhruv Kumar");
//        StringWriter writer = new StringWriter();
//        template.process(model, writer);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
//        String content = writer.toString();
//        helper.setText(content, true);
        helper.setText(html, true);
        mailSender.send(message);

    }
}
