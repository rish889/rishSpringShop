package com.rish889.rishShop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {
    @Schema(example = "iphone4")
    @NotBlank(message = "product_name must not be blank")
    @Size(min = 1, max = 255, message = "product_name size must be between 1 and 255")
    @JsonProperty("product_name")
    private String productName;

    @Schema(example = "10")
    @NotNull(message = "quantity must not be null")
    @Positive(message = "quantity must be greater than 0")
    @JsonProperty("quantity")
    private Long quantity;
}

