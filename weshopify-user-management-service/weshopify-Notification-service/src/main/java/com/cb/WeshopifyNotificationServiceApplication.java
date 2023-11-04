package com.cb;

import com.cb.rq.NotificationRq;
import com.cb.service.NotificationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Weshopify Notification SVC", version = "1.0",
        description = "This is the Weshopify Notification SVC API is for internal use",
        contact = @Contact(name = "CodeBrain Pvt. Ltd.",
                url = "http://www.codebrain.com/",
                email = "mailto:info@codebrain.com"),
        license = @License(
                name = "MIT License",
                url = "http://www.opensource.org/licenses/mit-license"
        ),
        termsOfService = "Weshopify Terms & Condition"
),
        servers = {
                @Server(
                        description = "Weshopify Local Environment",
                        url = "http://localhost:8061/dev"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "BearerAuth"
                )
        }
)
@SecurityScheme(
        name = "BearerAuth",
        description = "JWT Authorization",
        bearerFormat = "JWT",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
@SpringBootApplication
public class WeshopifyNotificationServiceApplication implements CommandLineRunner {

    @Autowired
    private NotificationService service;

    public static void main(String[] args) {
        SpringApplication.run(WeshopifyNotificationServiceApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
        NotificationRq rq = new NotificationRq();
        service.sendNotification(rq);
    }
}
