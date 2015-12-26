package com.arun.singh.exception.manager.loader;

import java.util.List;
import java.util.Optional;

public interface MetadataLoader<T> {
    public Optional<List<T>> load();
}
