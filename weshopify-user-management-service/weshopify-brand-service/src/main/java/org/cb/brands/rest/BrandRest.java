package org.cb.brands.rest;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cb.brands.rq.BrandRq;
import org.cb.brands.service.BrandService;
import org.cb.commons.base.datars.BaseDataRs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandRest {

    private final BrandService service;

    @PostMapping
//    @CircuitBreaker(name = "create-brands-circuitbreaker", fallbackMethod = "findCategoryByIdFallback")
//    @Retry(name = "create-brands-retry", fallbackMethod = "findCategoryByIdFallback")
    @RateLimiter(name = "create-brands-ratelimiter", fallbackMethod = "limitExcedeedFallback")
    public ResponseEntity<BaseDataRs> createBrand(@RequestBody @Valid BrandRq rq) {
        Optional.of(log.isDebugEnabled()).ifPresent(l -> log.debug("Executing Restful Service -> [ POST : /api/v1/brands]"));
        return ResponseEntity.ok(service.createBrand(rq));
    }

    public ResponseEntity<Object> findCategoryByIdFallback() {
        Map<String, Object> fallbackResponse = new HashMap<>();
        fallbackResponse.put("errorCode", HttpStatus.SERVICE_UNAVAILABLE.name());
        fallbackResponse.put("errorMessage",
            "Category Service is down at this moment. It will come up soon");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(fallbackResponse);
    }

    public ResponseEntity<Object> limitExcedeedFallback() {
        Map<String, Object> fallbackResponse = new HashMap<>();
        fallbackResponse.put("errorCode", HttpStatus.TOO_MANY_REQUESTS.name());
        fallbackResponse.put("errorMessage",
            "Configured Request Limit for an API has been excedeed. Please try after some time.");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(fallbackResponse);
    }

}
