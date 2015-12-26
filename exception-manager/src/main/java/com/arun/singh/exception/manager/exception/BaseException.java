package com.arun.singh.exception.manager.exception;

public class BaseException extends RuntimeException {
    String errorCode;
    String []params;

    public BaseException(String errorCode) {
        this.errorCode = errorCode;
    }

    public BaseException(String errorCode,String [] params) {
        this.errorCode = errorCode;
        this.params = params;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String[] getParams() {
        return params;
    }

}
