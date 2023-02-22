package com.rish889.order.service.controller;

import com.rish889.order.service.constant.OrderUrls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class HealthController {

    @GetMapping(OrderUrls.HEALTH)
    private Mono<String> healthCheck() {
        log.info("Rish Spring Shop Order Service Healthy");
        return Mono.just("Rish Spring Shop Order Service Healthy");
    }
}
