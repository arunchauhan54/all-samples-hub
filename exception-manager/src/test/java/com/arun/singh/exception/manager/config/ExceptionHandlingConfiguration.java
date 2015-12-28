package com.arun.singh.exception.manager.config;

import com.arun.singh.exception.manager.base.ExceptionHandler;
import com.arun.singh.exception.manager.base.ExceptionManager;
import com.arun.singh.exception.manager.config.ExceptionFlowConfiguration;
import com.arun.singh.exception.manager.handler.ExceptionLoggerHandler;
import com.arun.singh.exception.manager.handler.ExceptionMailHandler;
import com.arun.singh.exception.manager.handler.ExceptionNetcoolHandler;
import com.arun.singh.exception.manager.loader.DBMetadataLoader;
import com.arun.singh.exception.manager.loader.MetadataLoader;
import com.arun.singh.exception.manager.metadata.ExceptionMetaData;
import com.arun.singh.exception.manager.metadata.ExceptionMetaDataCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;


@Configuration
@Import(ExceptionFlowConfiguration.class)
public class ExceptionHandlingConfiguration {

    @Autowired
    Environment environment;

    @Bean
    public ExceptionManager exceptionManager() {
        ExceptionManager exceptionManager = new ExceptionManager();
        exceptionManager.setExceptionMetaDataCache(exceptionMetaDataCache());
        exceptionManager.setDefaultExceptionHandlers(getExceptionHandlers());
        return exceptionManager;
    }

    @Bean
    public ExceptionMetaDataCache exceptionMetaDataCache() {
        ExceptionMetaDataCache exceptionMetaDataCache = new ExceptionMetaDataCache();
        exceptionMetaDataCache.add("101", () -> (new ExceptionMetaData("101", "Got the Error : %s, Now I can handle this !")));
        exceptionMetaDataCache.setMetadataLoader(metadataLoader());
        return exceptionMetaDataCache;
    }

    @Bean
    public MetadataLoader metadataLoader() {
        return new DBMetadataLoader();
    }


    @Bean
    public List<ExceptionHandler> getExceptionHandlers() {
        List<ExceptionHandler> exceptionHandlers = new ArrayList<>();
        exceptionHandlers.add(exceptionLogger());
        exceptionHandlers.add(mailSender());
        exceptionHandlers.add(netcoolAlert());
        return exceptionHandlers;
    }

    @Bean
    public ExceptionHandler exceptionLogger() {
        return new ExceptionLoggerHandler();
    }

    @Bean
    public ExceptionHandler mailSender() {
        return new ExceptionMailHandler();
    }

    @Bean
    public ExceptionHandler netcoolAlert() {
        return new ExceptionNetcoolHandler();
    }

}
