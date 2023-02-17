package com.rish889.rishShop.service;

import com.rish889.rishShop.repository.ProductRepository;
import com.rish889.rishShop.model.Product;
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

    public Mono<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }

    public Mono<Product> createProduct(Product product) {
        return productRepository.save(product);
    }
}
