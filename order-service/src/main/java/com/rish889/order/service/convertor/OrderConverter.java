package com.rish889.order.service.convertor;

import com.rish889.order.service.dto.CreateOrderDto;
import com.rish889.order.service.dto.GetOrderDto;
import com.rish889.order.service.model.Order;

public class OrderConverter {
    public static Order convertFromDto(final CreateOrderDto dto) {
        return Order.builder()
                .userId(dto.getUserId())
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .build();
    }

    public static GetOrderDto convertToDto(final Order entity) {
        return GetOrderDto.builder()
                .orderId(entity.getOrderId())
                .userId(entity.getUserId())
                .productId(entity.getProductId())
                .quantity(entity.getQuantity())
                .build();
    }
}
