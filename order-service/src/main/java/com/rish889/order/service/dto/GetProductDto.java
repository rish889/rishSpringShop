package com.rish889.order.service.dto;

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
    private Long id;

    @Schema(example = "iphone4")
    @JsonProperty("product_name")
    private String productName;
}