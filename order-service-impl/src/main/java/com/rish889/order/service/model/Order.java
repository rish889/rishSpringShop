package com.rish889.order.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("orders")
@Builder
public class Order {
    @Id
    @Column(value = "order_id")
    private Long orderId;

    @Column(value = "user_id")
    private String userId;

    @Column(value = "product_id")
    private Long productId;

    @Column(value = "quantity")
    private Long quantity;
}

