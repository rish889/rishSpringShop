package com.rish889.product.service.controller;

import com.rish889.product.service.convertor.ProductConverter;
import com.rish889.product.service.service.ProductService;
import com.rish889.product.service.dto.CreateProductDto;
import com.rish889.product.service.dto.GetProductDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/products")
@Slf4j
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{productId}")
    public Mono<ResponseEntity<GetProductDto>> getProductById(@PathVariable @Positive(message = "ProductId must be greater than 0") @Parameter(example = "1") Long productId) {
        log.info("getProductById() : {}", productId);
        return productService.findById(productId)
                .map(ProductConverter::convertToDto)
                .map(u -> ResponseEntity.ok(u));
    }

    @PostMapping
    public Mono<ResponseEntity<GetProductDto>> createProduct(@Valid @RequestBody CreateProductDto dto) {
        log.info("createProduct() : {}", dto);

        return productService.createProduct(ProductConverter.convertFromDto(dto))
                .map(ProductConverter::convertToDto)
                .map(getProductDto -> ResponseEntity.ok(getProductDto));
    }
}
