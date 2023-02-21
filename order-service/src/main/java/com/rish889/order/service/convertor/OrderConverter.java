package com.rish889.order.service.convertor;

import com.rish889.order.service.dto.CreateOrderDto;
import com.rish889.order.service.dto.GetOrderDto;
import com.rish889.order.service.model.Product;

public class OrderConverter {
    public static Product convertFromDto(final CreateOrderDto dto) {
        return Product.builder()
                .productName(dto.getProductName())
                .build();
    }

    public static GetOrderDto convertToDto(final Product entity) {
        return GetOrderDto.builder()
                .orderId(entity.getId())
                .productName(entity.getProductName())
                .build();
    }
}
