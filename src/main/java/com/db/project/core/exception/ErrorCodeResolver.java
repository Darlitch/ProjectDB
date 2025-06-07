package com.db.project.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ErrorCodeResolver {
    private final Map<ErrorCode, HttpStatus> errorCodeMapping = Map.of(
            ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND
    );

    public HttpStatus resolveErrorCode(ErrorCode errorCode) {
        return errorCodeMapping.getOrDefault(errorCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
