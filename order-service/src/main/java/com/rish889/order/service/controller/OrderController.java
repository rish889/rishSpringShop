package com.rish889.order.service.controller;

import com.rish889.order.service.convertor.OrderConverter;
import com.rish889.order.service.dto.CreateOrderDto;
import com.rish889.order.service.dto.GetOrderDto;
import com.rish889.order.service.service.OrderService;
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
@RequestMapping("/v1/orders")
@Slf4j
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/{orderId}")
    public Mono<ResponseEntity<GetOrderDto>> getOrderById(@PathVariable @Positive(message = "OrderId must be greater than 0") @Parameter(example = "1") Long orderId) {
        log.info("getOrderById() : {}", orderId);
        return orderService.findById(orderId)
                .map(OrderConverter::convertToDto)
                .map(u -> ResponseEntity.ok(u));
    }

    @PostMapping
    public Mono<ResponseEntity<GetOrderDto>> createOrder(@Valid @RequestBody CreateOrderDto dto) {
        log.info("createOrder() : {}", dto);

        return orderService.createOrder(OrderConverter.convertFromDto(dto))
                .map(OrderConverter::convertToDto)
                .map(getOrderDto -> ResponseEntity.ok(getOrderDto));
    }
}
