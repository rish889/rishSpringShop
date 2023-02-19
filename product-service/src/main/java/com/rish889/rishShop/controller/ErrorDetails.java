package com.rish889.rishShop.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDetails {
    private String message;
}
