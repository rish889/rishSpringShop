package com.rish889.rishShop.controller;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;


@RestControllerAdvice
@Slf4j
class RestExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    Mono<ResponseEntity<ErrorDetails>> constraintViolation(ConstraintViolationException ex) {
        log.error("ExceptionMessage : {}.", ex.getMessage());
        return Mono.just(ResponseEntity.badRequest().body(ErrorDetails.builder().message(ex.getMessage()).build()));
    }

    @ExceptionHandler(BadRequestException.class)
    Mono<ResponseEntity<ErrorDetails>> badRequest(BadRequestException ex) {
        log.error("ExceptionMessage : {}.", ex.getMessage());
        return Mono.just(ResponseEntity.badRequest().body(ErrorDetails.builder().message(ex.getMessage()).build()));
    }

    @ExceptionHandler(Exception.class)
    Mono<ResponseEntity<ErrorDetails>> allExceptions(Exception ex) {
        log.error("Exception : {}.", ExceptionUtils.getStackTrace(ex));
        return Mono.just(ResponseEntity.internalServerError().body(ErrorDetails.builder().message("Something Went Wrong").build()));
    }
}
