package org.cb.brands.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cb.brands.rq.BrandRq;
import org.cb.brands.service.BrandService;
import org.cb.commons.base.datars.BaseDataRs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandRest {

    private final BrandService service;

    @PostMapping
    public ResponseEntity<BaseDataRs> createBrand(@RequestBody @Valid BrandRq rq) {
        Optional.of(log.isDebugEnabled()).ifPresent(l -> log.debug("Executing Restful Service -> [ POST : /api/v1/brands]"));
        return ResponseEntity.ok(service.createBrand(rq));
    }

}
