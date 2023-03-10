package com.rish889.order.service.service;

import com.rish889.adapter.product.service.ProductServiceAdapter;
import com.rish889.order.service.exception.BadRequestException;
import com.rish889.order.service.model.Order;
import com.rish889.order.service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductServiceAdapter productServiceAdapter;

    public Mono<Order> findByOrderId(Long orderId) {
        return orderRepository
                .findById(orderId)
                .flatMap(order -> productServiceAdapter.getProductByProductId(order.getProductId())
                        .log()
                        .map(getProductDto -> order))
                .switchIfEmpty(Mono.error(new BadRequestException("Order not found. OrderId : " + orderId)));
    }

    public Mono<Order> createOrder(Order order) {
        return orderRepository.save(order);
    }
}
