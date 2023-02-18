package com.rish889.rishShop.service;

import com.rish889.rishShop.model.Product;
import com.rish889.rishShop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void shouldGetUser() {
        when(productRepository.findById(1l)).thenReturn(
                Mono.just(Product.builder()
                        .productName("iPhone 10")
                        .build()));

        Mono<Product> actualProductMono = productService.findById(1l);

        StepVerifier
                .create(actualProductMono)
                .consumeNextWith(actualProduct -> {
                    assertEquals(
                            Product.builder().productName("iPhone 10").build(),
                            actualProduct);
                })
                .verifyComplete();
    }
}
