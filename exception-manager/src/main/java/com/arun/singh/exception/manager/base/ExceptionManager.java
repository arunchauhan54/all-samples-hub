package com.arun.singh.exception.manager.base;

import com.arun.singh.exception.manager.exception.BaseException;
import com.arun.singh.exception.manager.metadata.ExceptionMetaData;
import com.arun.singh.exception.manager.metadata.ExceptionMetaDataCache;
import org.springframework.integration.dsl.support.GenericHandler;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class ExceptionManager implements GenericHandler<BaseException> {
    private ExceptionMetaDataCache exceptionMetaDataCache = new ExceptionMetaDataCache();
    private List<ExceptionHandler> defaultExceptionHandlers;


    @Override
    public Object handle(BaseException exception, Map<String, Object> headers) {
        ExceptionMetaData metaData = exceptionMetaDataCache.getMetaDataForCode(exception.getErrorCode());
        metaData.getHandlers().ifPresent(handlers ->
                Stream.of(handlers).
                        forEach(handler ->
                                handler.handle(exception,metaData)));

        if (!metaData.getHandlers().isPresent()) {
            defaultExceptionHandlers.stream().
                    forEach(handler ->
                            handler.handle(exception,metaData));
        }

        return null;
    }

    @PostConstruct
    private void init() {
        Objects.requireNonNull(defaultExceptionHandlers);
    }

    public void setExceptionMetaDataCache(ExceptionMetaDataCache exceptionMetaDataCache) {
        this.exceptionMetaDataCache = exceptionMetaDataCache;
    }

    public void setDefaultExceptionHandlers(List<ExceptionHandler> defaultExceptionHandlers) {
        this.defaultExceptionHandlers = defaultExceptionHandlers;
    }
}
