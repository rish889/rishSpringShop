package com.rish889.rishShop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get a product by its id")
    @GetMapping("/{productId}")
    public Mono<ResponseEntity<Product>> getProductById(@PathVariable Long productId) {
        logger.info("getProductById() : {}", productId);
        Mono<Product> productMono = productService.findById(productId);
        return productMono.map(u -> ResponseEntity.ok(u));
    }

    @Operation(summary = "Create a product")
    @PostMapping
    public Mono<ResponseEntity<Product>> createProduct(@RequestBody Product product) {
        logger.info("createProduct() : {}", product);
        Mono<Product> productMono = productService.createProduct(product);
        return productMono.map(u -> ResponseEntity.ok(u));
    }
}
