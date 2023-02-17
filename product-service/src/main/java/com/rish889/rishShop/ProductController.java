package com.rish889.rishShop;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public Mono<ResponseEntity<Product>> getProductById(@PathVariable @Parameter(example = "1") Long productId) {
        logger.info("getProductById() : {}", productId);
        try {
            Mono<Product> productMono = productService.findById(productId);
            return productMono.map(u -> ResponseEntity.ok(u));
        } catch (Exception ex) {
            return Mono.just(ResponseEntity.internalServerError().body(null));
        }
    }

    @PostMapping
    public Mono<ResponseEntity<Product>> createProduct(@RequestBody Product product) {
        logger.info("createProduct() : {}", product);
        try {
            Mono<Product> productMono = productService.createProduct(product);
            return productMono.map(u -> ResponseEntity.ok(u));
        } catch (Exception ex) {
            return Mono.just(ResponseEntity.internalServerError().body(null));
        }
    }
}
