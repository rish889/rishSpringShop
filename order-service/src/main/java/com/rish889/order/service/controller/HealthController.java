package com.rish889.order.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        log.info("Rish Spring Shop Order Service Healthy");
        return Mono.just("Rish Spring Shop Order Service Healthy");
    }
}