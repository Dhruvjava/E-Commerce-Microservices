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

  @GetMapping("/brand-fallback")
  public Mono<String> brandFallback() {

    return Mono.just(
        "Our Brand service is undergoing maintenance. Please try again in a few minutes. Thank you for your patience.");
  }

  @GetMapping("/cat-fallback")
  public Mono<String> catFallback() {

    return Mono.just(
        "Our Category service is undergoing maintenance. Please try again in a few minutes. Thank you for your patience.");
  }

  @GetMapping("/notif-fallback")
  public Mono<String> notifFallback() {

    return Mono.just(
        "Our Category service is undergoing maintenance. Please try again in a few minutes. Thank you for your patience.");
  }

  @GetMapping("/user-fallback")
  public Mono<String> userfFallback() {

    return Mono.just(
        "Our User Management service is undergoing maintenance. Please try again in a few minutes. Thank you for your patience.");
  }
}
