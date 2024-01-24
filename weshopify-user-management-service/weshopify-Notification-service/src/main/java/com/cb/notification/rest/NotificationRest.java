package com.cb.notification.rest;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.notification.service.INotificationService;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.cb.commons.email.rq.NotificationRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/notifications")
@Slf4j
public class NotificationRest {

    @Autowired
    private INotificationService service;

    @PostMapping
    public ResponseEntity<?> sendNotification(@RequestBody NotificationRq rq)
                    throws TemplateException, IOException {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTfull Services : [ /api/v1/notifications :POST] -> ");
        }
        try {
            BaseDataRs rs = service.sendNotification(rq);
            return ResponseEntity.ok(rs);
        } catch (Exception e) {
            log.error("Exception in RESTfull Services : [ /api/v1/notifications :POST] -> ", e);
            throw e;
        }
    }
}
