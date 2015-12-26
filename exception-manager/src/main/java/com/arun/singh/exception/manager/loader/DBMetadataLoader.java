package com.arun.singh.exception.manager.loader;

import com.arun.singh.exception.manager.base.ExceptionHandler;
import com.arun.singh.exception.manager.metadata.ExceptionMetaData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBMetadataLoader implements  MetadataLoader<ExceptionMetaData>{
    @Autowired
    ExceptionHandler netcoolAlert;

    @Override
    public Optional<List<ExceptionMetaData>> load() {
        // read data from DB and return is as a list of ExceptionMetaData
        List<ExceptionMetaData> exceptionMetaDatas = new ArrayList<>();
        exceptionMetaDatas.add(new ExceptionMetaData("102", "Invalid Number found while processing : %s"));
        return Optional.ofNullable(exceptionMetaDatas);
    }
}
