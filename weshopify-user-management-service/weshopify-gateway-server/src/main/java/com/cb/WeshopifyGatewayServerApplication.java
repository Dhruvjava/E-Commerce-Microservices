package com.cb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
public class WeshopifyGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeshopifyGatewayServerApplication.class, args);
	}

//	@Bean
//	public KeyResolver keyResolver() {
//		return exchange -> Mono.just("weshopify-gateway-server-cache");
//	}

	@Bean
	public KeyResolver keyResolver() {
		return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("Authorization"));
	}


}
