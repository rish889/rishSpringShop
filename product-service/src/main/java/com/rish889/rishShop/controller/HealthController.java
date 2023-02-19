package com.rish889.rishShop.controller;

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
        log.info("Rish Shop Healthy 1");
        return Mono.just("Rish Shop Healthy 1");
    }
}
