package com.fusionkoding.scfleetsapi.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
class RestExceptionHelper {
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleValidationExceptions(DataIntegrityViolationException ex) {
        log.error("Validation Exception: ", ex);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundExceptions(NotFoundException ex) {
        log.error("NotFoundException: ", ex);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.error("MethodArgumentTypeMismatchException", ex);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        log.error("Exception", ex);
        return ResponseEntity.badRequest().build();
    }
}