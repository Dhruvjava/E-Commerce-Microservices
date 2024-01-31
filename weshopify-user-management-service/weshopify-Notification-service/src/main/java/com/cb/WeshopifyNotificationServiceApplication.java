package com.cb;

import com.cb.notification.service.INotificationService;
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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@OpenAPIDefinition(info = @Info(title = "Weshopify Notification SVC", version = "1.0",
                description = "This is the Weshopify Notification SVC API is for internal use. And This application is used to maintain Notifications functionality.",
                contact = @Contact(name = "CodeBrain Pvt. Ltd.", url = "http://www.codebrain.com/",
                                email = "mailto:info@codebrain.com"),
                license = @License(name = "MIT License",
                                url = "http://www.opensource.org/licenses/mit-license"),
                termsOfService = "Weshopify Terms & Condition"),
                servers = {@Server(description = "Weshopify Local Environment",
                                url = "http://localhost:8061/dev")},
                security = {@SecurityRequirement(name = "BearerAuth")})
@SecurityScheme(name = "BearerAuth", description = "JWT Authorization", bearerFormat = "JWT",
                scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@SpringBootApplication
@EnableDiscoveryClient
public class WeshopifyNotificationServiceApplication {

    @Autowired
    private INotificationService service;

    public static void main(String[] args) {
        SpringApplication.run(WeshopifyNotificationServiceApplication.class, args);


    }

    @Bean(name = "errorProperties")
    public MessageSource errorProperties() {
        ReloadableResourceBundleMessageSource messageSource =
                        new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/bundles/application_errors");
        // messageSource.setCacheSeconds(10); //reload messages every 10 seconds
        return messageSource;
    }

    @Bean(name = "messageProperties")
    public MessageSource messageProperties() {
        ReloadableResourceBundleMessageSource messageSource =
                        new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/bundles/application_messages");
        // messageSource.setCacheSeconds(10); //reload messages every 10 seconds
        return messageSource;
    }

    @Bean(name = "emailProperties")
    public MessageSource emailProperties() {
        ReloadableResourceBundleMessageSource messageSource =
                        new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/bundles/email_templates");
        // messageSource.setCacheSeconds(10); //reload messages every 10 seconds
        return messageSource;
    }

}
