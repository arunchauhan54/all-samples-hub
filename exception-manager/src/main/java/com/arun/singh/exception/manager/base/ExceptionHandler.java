package com.arun.singh.exception.manager.base;

import com.arun.singh.exception.manager.exception.BaseException;
import com.arun.singh.exception.manager.metadata.ExceptionMetaData;

public interface ExceptionHandler<T extends BaseException> {
    void handle(T t, ExceptionMetaData exceptionMetaData);
}
