package com.rish889.product.service.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorDetails {
    @JsonProperty("error_messages")
    private List<String> messages;
}
