package com.arun.singh.exception.manager.metadata;

import com.arun.singh.exception.manager.base.ExceptionHandler;

import java.util.Optional;

public class ExceptionMetaData {
    private String errorCode;
    private String message;
    private ExceptionHandler[] exceptionHandlers;

    public ExceptionMetaData(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ExceptionMetaData(String errorCode, String message, ExceptionHandler... exceptionHandlers) {
        this.errorCode = errorCode;
        this.message = message;
        this.exceptionHandlers = exceptionHandlers;
    }

    public Optional<ExceptionHandler[]> getHandlers() {
        return Optional.ofNullable(exceptionHandlers);
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
