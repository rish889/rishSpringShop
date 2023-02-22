package com.rish889.adapter.product.service.client;

import com.rish889.product.service.dto.GetProductDto;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@EnableWebFlux
@Component
public class ProductServiceAdapter {
    private final int MAX_RETRIES = 3;
    private final int RETRY_DELAY = 10;

//    @Autowired
//    private WebClient webClient;

    public Mono<GetProductDto> getProductByProductId() {
        final String uri = "/v1/products/1";

        return productServiceClient()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<GetProductDto>() {
                })
                .retryWhen(Retry.fixedDelay(MAX_RETRIES, Duration.ofSeconds(RETRY_DELAY)).filter((t) -> true));
    }

    public WebClient productServiceClient() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

        WebClient client = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("http://localhost:8080")
                .build();
        return client;
    }
}
