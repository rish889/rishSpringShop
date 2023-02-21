package com.rish889.order.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDto {
    @Schema(example = "iphone4")
    @NotBlank(message = "product_name must not be blank")
    @JsonProperty("product_name")
    private String productName;

    @Schema(example = "10")
    @NotNull(message = "quantity must not be null")
    @Positive(message = "quantity must be greater than 0")
    @JsonProperty("quantity")
    private Long quantity;
}

