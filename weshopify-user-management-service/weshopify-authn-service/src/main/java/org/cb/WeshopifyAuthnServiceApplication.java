package org.cb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WeshopifyAuthnServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeshopifyAuthnServiceApplication.class, args);
    }

}
