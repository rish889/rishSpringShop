package com.rish889.rishShop.convertor;

import com.rish889.rishShop.dto.CreateProductDto;
import com.rish889.rishShop.dto.GetProductDto;
import com.rish889.rishShop.model.Product;

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
