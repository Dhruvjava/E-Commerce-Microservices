package org.cb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
@EnableDiscoveryClient
public class WeshopifyBrandServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeshopifyBrandServiceApplication.class, args);
	}

	@Bean
	MessageSource errorSource(){
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:/bundles/application_error.properties");
		return source;
	}

	@Bean
	MessageSource messageSource(){
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:/bundles/application_message.properties");
		return source;
	}

}
