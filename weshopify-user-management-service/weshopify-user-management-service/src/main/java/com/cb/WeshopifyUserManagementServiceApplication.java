package com.cb;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Weshopify User management SVC", version = "1.0",
        description = "This is the Weshopify User management SVC API is for internal use",
        contact = @Contact(name = "CodeBrain Pvt. Ltd.",
                url = "http://www.codebrain.com/",
                email = "mailto:info@codebrain.com"),
        license = @License(
                name = "MIT License",
                url = "http://www.opensource.org/licenses/mit-license"
        ),
        termsOfService = "Weshopify Term & Condkitions"
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
public class WeshopifyUserManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeshopifyUserManagementServiceApplication.class, args);
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
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


//    @Bean(name = "reportProperties")
//    public MessageSource reportProperties() {
//        ReloadableResourceBundleMessageSource messageSource =
//                new ReloadableResourceBundleMessageSource();
//        messageSource.setBasenames("classpath:/bundles/report_templates");
//        // messageSource.setCacheSeconds(10); //reload messages every 10 seconds
//        return messageSource;
//    }

//    @Bean(name = "emailProperties")
//    public MessageSource emailProperties() {
//        ReloadableResourceBundleMessageSource messageSource =
//                new ReloadableResourceBundleMessageSource();
//        messageSource.setBasenames("classpath:/bundles/email_templates");
//        // messageSource.setCacheSeconds(10); //reload messages every 10 seconds
//        return messageSource;
//    }

}
