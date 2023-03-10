package com.rish889.product.service.convertor;

import com.rish889.product.service.dto.CreateProductDto;
import com.rish889.product.service.dto.GetProductDto;
import com.rish889.product.service.model.Product;

public class ProductConverter {
    public static Product convertFromDto(final CreateProductDto dto) {
        return Product.builder()
                .productName(dto.getProductName())
                .quantity(dto.getQuantity())
                .build();
    }

    public static GetProductDto convertToDto(final Product entity) {
        return GetProductDto.builder()
                .productId(entity.getProductId())
                .productName(entity.getProductName())
                .quantity(entity.getQuantity())
                .build();
    }
}
