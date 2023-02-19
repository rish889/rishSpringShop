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
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
class RestExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    Mono<ResponseEntity<ErrorDetails>> constraintViolation(ConstraintViolationException ex) {
        log.error("ExceptionMessage : {}.", ex.getMessage());
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .messages(ex.getConstraintViolations().stream()
                        .map(constraintViolation -> constraintViolation.getMessage())
                        .collect(Collectors.toList()))
                .build();
        return Mono.just(ResponseEntity.badRequest().body(errorDetails));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    Mono<ResponseEntity<ErrorDetails>> webExchangeBindException(WebExchangeBindException ex) {
        log.error("ExceptionMessage : {}.", ex.getMessage());
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .messages(ex.getAllErrors().stream()
                        .map(fieldError -> fieldError.getDefaultMessage())
                        .collect(Collectors.toList()))
                .build();
        return Mono.just(ResponseEntity.badRequest().body(errorDetails));
    }

    @ExceptionHandler(BadRequestException.class)
    Mono<ResponseEntity<ErrorDetails>> badRequest(BadRequestException ex) {
        log.error("ExceptionMessage : {}.", ex.getMessage());
        return Mono.just(ResponseEntity.badRequest().body(ErrorDetails.builder().messages(Arrays.asList(ex.getMessage())).build()));
    }

    @ExceptionHandler(Exception.class)
    Mono<ResponseEntity<ErrorDetails>> allExceptions(Exception ex) {
        log.error("Exception : {}.", ExceptionUtils.getStackTrace(ex));
        return Mono.just(ResponseEntity.internalServerError().body(ErrorDetails.builder().messages(Arrays.asList("Something Went Wrong")).build()));
    }
}
