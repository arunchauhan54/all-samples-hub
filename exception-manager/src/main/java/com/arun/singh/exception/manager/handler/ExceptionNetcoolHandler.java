package com.arun.singh.exception.manager.handler;

import com.arun.singh.exception.manager.base.ExceptionHandler;
import com.arun.singh.exception.manager.exception.BaseException;
import com.arun.singh.exception.manager.metadata.ExceptionMetaData;

public class ExceptionNetcoolHandler implements ExceptionHandler {
    @Override
    public void handle(BaseException e, ExceptionMetaData exceptionMetaData) {
        System.out.println("ExceptionNetcoolHandler.handle "+  String.format(exceptionMetaData.getMessage(),(Object[])e.getParams()));
    }
}
