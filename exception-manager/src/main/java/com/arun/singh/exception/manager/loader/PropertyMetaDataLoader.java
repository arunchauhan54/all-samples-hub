package com.arun.singh.exception.manager.loader;

import com.arun.singh.exception.manager.metadata.ExceptionMetaData;

import java.util.List;
import java.util.Optional;

public class PropertyMetaDataLoader implements  MetadataLoader<ExceptionMetaData>{
    @Override
    public Optional<List<ExceptionMetaData>> load() {
        // just an example. Its optional to implement it. May be in future if need comes.
        return  Optional.empty();
    }
}
