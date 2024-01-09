package com.cb.rest;

import com.cb.service.NotificationService;
import freemarker.template.TemplateException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.cb.commons.notification.rq.NotificationRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/notification")
@Slf4j
@Tag(name = "NOTIFICATION SERVICE")
public class NotificationRest {

    @Autowired
    private NotificationService service;

    public ResponseEntity<?> emailVerification(@RequestBody NotificationRq rq){
        if (log.isDebugEnabled()){
            log.debug("Executing emailVerification(NotificationRq rq) ->");
        }
        try{
            service.sendNotification(rq);
            return ResponseEntity.ok("Notification Sent !!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
