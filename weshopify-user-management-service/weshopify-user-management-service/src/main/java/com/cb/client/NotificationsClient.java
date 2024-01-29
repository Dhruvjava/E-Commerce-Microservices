package com.cb.client;

import com.cb.base.data.rs.BaseDataRs;
import org.cb.commons.email.rq.NotificationRq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@FeignClient("WESHOPIFY-NOTIFICATION-SVC")
public interface NotificationsClient {

    @PostMapping("/dev/api/v1/notifications")
    public ResponseEntity<BaseDataRs> sendNotification(@RequestBody NotificationRq rq);

}
