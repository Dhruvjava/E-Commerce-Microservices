package org.cb;

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
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "WESHOPIFY-CATEGORIES-SVC",
                description = "This Service is Weshopify-Categories-Svc application. This module will going to handle functionality related to categories management",
                version = "1.0",
                contact = @Contact(name = "CodeBrains PVT. LTD.", url = "https://codebrains.com",
                                email = "mailto:info@codebrains.com"),
                termsOfService = "Weshopify Terms & Conditions",
                license = @License(name = "MIT License",
                                url = "http://www.opensource.org/licenses/mit-license")),
                servers = {@Server(description = "WESHOPIFY-CATEGORIES-SVC-LOCAL-ENV",
                                url = "http://localhost:8066/dev")},
                security = {@SecurityRequirement(name = "BearerAuth")})
@SecurityScheme(name = "BearerAuth", description = "JWT Authorization", bearerFormat = "JWT",
                scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class WeshopifyCategoriesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeshopifyCategoriesServiceApplication.class, args);
    }

    @Bean
    MessageSource errorSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:/bundles/application_error");
        return source;
    }

    @Bean
    MessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:/bundles/application_message");
        return source;
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
