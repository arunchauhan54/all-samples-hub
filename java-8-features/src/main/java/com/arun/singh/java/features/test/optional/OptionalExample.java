package com.arun.singh.java.features.test.optional;

import java.util.Optional;

/**
 * Created by Arun on 4/17/2016.
 */
public class OptionalExample {
    public static void main(String[] args) {

        Part part = new Part("Test", 1l);
        Optional.of(part.getName())
                .filter(s -> s.equals("Test"))
                .orElseThrow(() -> new IllegalArgumentException("Not valid"));

    }

}

class Part {
    private String name;
    private Long size;

    public Part(String name, Long size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public Long getSize() {
        return size;
    }
}