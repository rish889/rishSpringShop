package com.rish889.product.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
@Slf4j
public class HealthController {

    @GetMapping
    private Mono<String> healthCheck() {
        log.info("Rish Spring Shop Product Service Healthy");
        return Mono.just("Rish Spring Shop Product Service Healthy");
    }
}
