package com.rish889.rishShop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProductDto {

    private Long id;

    @Schema(example = "iphone4")
    private String productName;
}