package com.rish889.order.service.controller;

import com.rish889.order.service.constant.OrderUrls;
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
@RequestMapping
@Slf4j
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = (OrderUrls.GET_ORDER_BY_ORDER_ID_V1))
    public Mono<ResponseEntity<GetOrderDto>> getOrderByOrderId(@PathVariable @Positive(message = "OrderId must be greater than 0") @Parameter(example = "1") Long orderId) {
        log.info("getOrderByOrderId() : {}", orderId);
        return orderService.findByOrderId(orderId)
                .map(OrderConverter::convertToDto)
                .map(u -> ResponseEntity.ok(u));
    }

    @PostMapping(value = (OrderUrls.CREATE_ORDER_V1))
    public Mono<ResponseEntity<GetOrderDto>> createOrder(@Valid @RequestBody CreateOrderDto dto) {
        log.info("createOrder() : {}", dto);

        return orderService.createOrder(OrderConverter.convertFromDto(dto))
                .map(OrderConverter::convertToDto)
                .map(getOrderDto -> ResponseEntity.ok(getOrderDto));
    }
}
