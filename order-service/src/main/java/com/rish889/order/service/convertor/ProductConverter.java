package com.rish889.order.service.convertor;

import com.rish889.order.service.dto.CreateProductDto;
import com.rish889.order.service.dto.GetProductDto;
import com.rish889.order.service.model.Product;

public class ProductConverter {
    public static Product convertFromDto(final CreateProductDto dto) {
        return Product.builder()
                .productName(dto.getProductName())
                .build();
    }

    public static GetProductDto convertToDto(final Product entity) {
        return GetProductDto.builder()
                .id(entity.getId())
                .productName(entity.getProductName())
                .build();
    }
}
