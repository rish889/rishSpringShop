package com.rish889.rishShop;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("products")
public class Product {

    @Id
    private Long id;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(value = "product_name")
    private String productName;
}

