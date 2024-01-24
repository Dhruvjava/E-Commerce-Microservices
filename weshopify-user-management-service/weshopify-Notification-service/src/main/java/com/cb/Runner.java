package com.cb;

import com.cb.notification.service.INotificationService;
import lombok.RequiredArgsConstructor;
import org.cb.commons.email.rq.NotificationRq;
import org.cb.commons.email.rs.EmailNameRs;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    final INotificationService service;



    @Override
    public void run(String... args) throws Exception {
        NotificationRq rq = NotificationRq.builder().username("Dhruv k").to(new EmailNameRs("d.k.dhakarey1999@gmail.com", "Dhruv Kumar")).subject("Email Verification").build();
        service.sendNotification(rq);
    }
}
