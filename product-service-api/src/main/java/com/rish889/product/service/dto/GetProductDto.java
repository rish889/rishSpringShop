package com.rish889.product.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetProductDto {

    @Schema(example = "1")
    @JsonProperty("product_id")
    private Long productId;

    @Schema(example = "iphone4")
    @JsonProperty("product_name")
    private String productName;

    @Schema(example = "10")
    @JsonProperty("quantity")
    private Long quantity;
}