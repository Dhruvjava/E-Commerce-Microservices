package com.cb.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class WeshopifyGatewayFallback {

  @GetMapping("/auth-fallback")
  public Mono<String> authnFallback() {

    return Mono.just(
        "Our Authentication service is undergoing maintenance. Please try again in a few minutes. Thank you for your patience.");
  }
}
