package com.rish889.product.service.client;

import com.rish889.product.service.dto.GetProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
@Component
public class ProductServiceClient {
    private final int MAX_RETRIES = 3;
    private final int RETRY_DELAY = 10;

    @Autowired
    private WebClient webClient;

    public Mono<GetProductDto> getProductByProductId() {
        final String uri = "/v1/products/1";

        return webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<GetProductDto>() {
                })
                .retryWhen(Retry.fixedDelay(MAX_RETRIES, Duration.ofSeconds(RETRY_DELAY)).filter((t) -> true));
    }
}
