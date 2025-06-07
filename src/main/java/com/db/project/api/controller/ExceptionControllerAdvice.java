package com.db.project.api.controller;

import com.db.project.api.dto.exception.ErrorResponse;
import com.db.project.core.exception.ErrorCodeResolver;
import com.db.project.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerAdvice {

    private final ErrorCodeResolver errorCodeResolver;

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(ServiceException ex) {
        log.info("APP ERROR [{}] : {}", ex.getErrorCode(), ex.getMessage());
        return ResponseEntity
                .status(errorCodeResolver.resolveErrorCode(ex.getErrorCode()))
                .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        log.error("UNEXPECTED ERROR : {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ex.getMessage()));
    }
}
