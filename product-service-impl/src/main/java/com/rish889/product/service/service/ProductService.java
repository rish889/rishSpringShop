package com.rish889.product.service.service;

import com.rish889.product.service.exception.BadRequestException;
import com.rish889.product.service.model.Product;
import com.rish889.product.service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Mono<Product> findByProductId(Long productId) {
        return productRepository
                .findById(productId)
                .switchIfEmpty(Mono.error(new BadRequestException("Product not found. ProductId : " + productId)));
    }

    public Mono<Product> createProduct(Product product) {
        return productRepository.save(product);
    }
}
