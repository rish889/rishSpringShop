package com.rish889.order.service.repository;

import com.rish889.order.service.model.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
    Mono<Order> findByOrderId(String orderId);

}
