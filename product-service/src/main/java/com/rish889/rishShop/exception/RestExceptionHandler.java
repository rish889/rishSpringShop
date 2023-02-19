package com.rish889.rishShop.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
class RestExceptionHandler {
    @ExceptionHandler(Exception.class)
    Mono<ResponseEntity<ErrorDetails>> allOtherExceptions(Exception ex) {
        log.error("Exception : {}.", ExceptionUtils.getStackTrace(ex));
        final ErrorDetails errorDetails = ErrorDetails.builder().messages(Arrays.asList("Something Went Wrong")).build();
        return Mono.just(ResponseEntity.internalServerError().body(errorDetails));
    }

    @ExceptionHandler(BadRequestException.class)
    Mono<ResponseEntity<ErrorDetails>> badRequest(BadRequestException ex) {
        log.error("ExceptionMessage : {}.", ex.getMessage());
        final ErrorDetails errorDetails = ErrorDetails.builder().messages(Arrays.asList(ex.getMessage())).build();
        return Mono.just(ResponseEntity.badRequest().body(errorDetails));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    Mono<ResponseEntity<ErrorDetails>> constraintViolation(ConstraintViolationException ex) {
        log.error("ExceptionMessage : {}.", ex.getMessage());
        final List<String> errorMessages = ex.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .collect(Collectors.toList());
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .messages(errorMessages)
                .build();

        return Mono.just(ResponseEntity.badRequest().body(errorDetails));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    Mono<ResponseEntity<ErrorDetails>> webExchangeBindException(WebExchangeBindException ex) {
        log.error("ExceptionMessage : {}.", ex.getMessage());
        final List<String> errorMessages = ex.getAllErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .messages(errorMessages)
                .build();
        return Mono.just(ResponseEntity.badRequest().body(errorDetails));
    }
}
