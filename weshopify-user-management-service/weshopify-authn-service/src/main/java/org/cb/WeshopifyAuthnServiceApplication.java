package org.cb;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "WESHOPIFY-AUTHN-SERVICE",
                description = "This is Weshopify-Authn-Service. This service is used to Authenticate and Authorization for users.",
                version = "1.0",
                contact = @Contact(name = "CodeBrains PVT. LTD.", url = "https://codebrains.com",
                                email = "mailto:info@codebrains.com"),
                termsOfService = "Weshopify Tearms & Conditions",
                license = @License(name = "MIT License",
                                url = "http://www.opensource.org/licenses/mit-license")),
                servers = {@Server(description = "WESHOPIFY-AUTHN-SERVICE-LOCAL-ENV",
                                url = "http://localhost:8064/dev")},
                security = {@SecurityRequirement(name = "BearerAuth")})
@SecurityScheme(name = "BearerAuth", description = "JWT Authorization", bearerFormat = "JWT",
                scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class WeshopifyAuthnServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeshopifyAuthnServiceApplication.class, args);
    }

    @Bean
    public MessageSource errorSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasenames("classpath:/bundles/application_error");
        return source;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasenames("classpath:/bundles/application_message");
        return source;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
