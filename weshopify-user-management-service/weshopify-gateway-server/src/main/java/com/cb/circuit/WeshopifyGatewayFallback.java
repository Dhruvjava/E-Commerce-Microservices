package com.cb.circuit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("/api/gateway")
public class WeshopifyGatewayFallback {

  @GetMapping("/authn/failed")
  public Mono<?> authnFallback() {

    return Mono.just(
        "Our Authentication service is undergoing maintenance. Please try again in a few minutes. Thank you for your patience.");
  }
}
