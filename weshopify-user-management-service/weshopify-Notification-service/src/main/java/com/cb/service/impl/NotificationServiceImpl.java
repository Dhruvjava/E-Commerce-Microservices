package com.cb.service.impl;

import com.cb.Messages;
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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
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
        String templateName = messages.getEmailProperty("email.signup.template.name");
        Template template = templateConfig.getTemplate(templateName);
        Map<String, Object> model = new ModelMap();
//        Map<String, Object> model = new HashMap<>();
        model.put("username", "Dhruv Kumar");
        model.put("verificationUrl", "Dhruv Kumar");
        model.put("appname", "CodeBrains");
        model.put("loginpage", "go to link");
        model.put("trial_length", "30");
        LocalDate trialStartDate = LocalDate.now();
        model.put("trial_start_date", trialStartDate);
        LocalDate trialEndDate = trialStartDate.plusDays(30);
        model.put("trial_end_date", trialEndDate);
        model.put("support_email", "jcodebrains@gmail.com");
        model.put("live_chat_url", "#");
        model.put("sender", fromEmail);
        model.put("priductTeam", "CodeBrains Team");
        model.put("help_url", "#");
        model.put("action_url", "#");
        model.put("login_page", "#");
//        StringWriter writer = new StringWriter();
//        template.process(model, writer);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
//        String content = writer.toString();
//        helper.setText(content, true);
        helper.setText(html, true);
        mailSender.send(message);

    }
}
