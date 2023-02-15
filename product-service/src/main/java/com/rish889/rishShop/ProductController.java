package com.rish889.rishShop;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public Mono<ResponseEntity<Product>> getProductById(@PathVariable Long productId) {
        logger.info("getProductById : {}", productId);
        Mono<Product> productMono = productService.findById(productId);
        return productMono.map(u -> ResponseEntity.ok(u));
    }
}
