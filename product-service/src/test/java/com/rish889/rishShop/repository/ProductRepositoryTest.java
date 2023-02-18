package com.rish889.rishShop.repository;

import com.rish889.rishShop.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataR2dbcTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        Mono<Product> actualProductMono = productRepository.findById(1l);

        StepVerifier
                .create(actualProductMono)
                .consumeNextWith(actualProduct -> {
                    assertEquals(
                            Product.builder().id(1l).productName("iPhone 10").build(),
                            actualProduct);
                })
                .verifyComplete();
    }
}