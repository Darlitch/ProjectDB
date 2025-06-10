package com.db.project.core.exception;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ErrorCodeResolver {
    private final Map<ErrorCode, HttpStatus> errorCodeMapping = Map.of(
            ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND,
            ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST
    );

    public HttpStatus resolveErrorCode(ErrorCode errorCode) {
        return errorCodeMapping.getOrDefault(errorCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
