package com.rish889.rishShop.controller;

import com.rish889.product.service.controller.ProductController;
import com.rish889.product.service.model.Product;
import com.rish889.product.service.security.SpringSecurityConfiguration;
import com.rish889.product.service.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(ProductController.class)
@Import(SpringSecurityConfiguration.class)
class ProductControllerTest {
    @Autowired
    private WebTestClient webClient;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetProduct() {
        when(productService.findByProductId(1l)).thenReturn(
                Mono.just(Product.builder()
                        .productName("iPhone 10")
                        .build()));
        webClient
                .get()
                .uri("/v1/products/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody().jsonPath("product_name").isEqualTo("iPhone 10");
    }
}
