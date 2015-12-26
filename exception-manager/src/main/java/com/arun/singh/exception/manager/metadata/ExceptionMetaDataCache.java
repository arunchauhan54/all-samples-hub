package com.arun.singh.exception.manager.metadata;

import com.arun.singh.exception.manager.loader.MetadataLoader;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class ExceptionMetaDataCache {

    private Map<String, ExceptionMetaData> metaDataCache = new ConcurrentHashMap<>();
    private ExceptionMetaData defaultExceptionMetaData = new ExceptionMetaData("-1", "A Base exception for which error code is not Configured: %s");
    private MetadataLoader<ExceptionMetaData> metadataLoader;

    public ExceptionMetaData getMetaDataForCode(String errorCode) {
        return metaDataCache.getOrDefault(errorCode, defaultExceptionMetaData);
    }

    public void add(String errorCde, Supplier<ExceptionMetaData> exceptionMetaData) {
        metaDataCache.put(errorCde, exceptionMetaData.get());
    }

    @PostConstruct
    public void init() {
        metaDataCache.put("000",new ExceptionMetaData("-1", "A runtime Exception occured: %s"));
        if (metadataLoader != null)
            metadataLoader.load().ifPresent(allMetaData ->
                    allMetaData.forEach(exceptionMetaData ->
                            metaDataCache.put(exceptionMetaData.getErrorCode(), exceptionMetaData)));

    }

    public void setMetadataLoader(MetadataLoader metadataLoader) {
        this.metadataLoader = metadataLoader;
    }
}
