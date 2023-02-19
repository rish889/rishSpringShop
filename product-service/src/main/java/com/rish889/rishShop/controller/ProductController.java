package com.rish889.rishShop.controller;

import com.rish889.rishShop.convertor.ProductConverter;
import com.rish889.rishShop.dto.CreateProductDto;
import com.rish889.rishShop.dto.GetProductDto;
import com.rish889.rishShop.service.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@Slf4j
@Validated
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public Mono<ResponseEntity<GetProductDto>> getProductById(@PathVariable @Positive @Parameter(example = "1") Long productId) {
        logger.info("getProductById() : {}", productId);
        try {
            return productService.findById(productId)
                    .map(ProductConverter::convertToDto)
                    .map(u -> ResponseEntity.ok(u))
                    .switchIfEmpty(Mono.error(new RuntimeException("csdvsvs")));
        } catch (Exception ex) {
            return Mono.just(ResponseEntity.internalServerError().body(null));
        }
    }

    @PostMapping
    public Mono<ResponseEntity<GetProductDto>> createProduct(@Valid @RequestBody CreateProductDto dto) {
        logger.info("createProduct() : {}", dto);
        try {
            return productService.createProduct(ProductConverter.convertFromDto(dto))
                    .map(ProductConverter::convertToDto)
                    .map(getProductDto -> ResponseEntity.ok(getProductDto));
        } catch (Exception ex) {
            return Mono.just(ResponseEntity.internalServerError().body(null));
        }
    }
}
