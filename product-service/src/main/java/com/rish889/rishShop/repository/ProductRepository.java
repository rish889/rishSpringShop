package com.rish889.rishShop.repository;

import com.rish889.rishShop.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Mono<Product> findById(String id);

}
