package com.rish889.adapter.product.service;

import com.rish889.product.service.constant.ProductUrls;
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
public class ProductServiceAdapter {
    private final int MAX_RETRIES = 3;
    private final int RETRY_DELAY = 10;

    @Autowired
    private WebClient productServiceWebClient;

    public Mono<GetProductDto> getProductByProductId(Long productId) {
        final String uri = ProductUrls.GET_PRODUCT_BY_PRODUCT_ID_V1_PREFIX + productId;

        return productServiceWebClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<GetProductDto>() {
                })
                .retryWhen(Retry.fixedDelay(MAX_RETRIES, Duration.ofSeconds(RETRY_DELAY)).filter((t) -> true));
    }
}
