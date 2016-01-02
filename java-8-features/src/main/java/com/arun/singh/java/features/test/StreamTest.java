package com.arun.singh.java.features.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        list.add("Abc");
        list.add("Xyz");
        System.out.println(list.stream()
                .filter(string -> string.startsWith("A"))
                .mapToInt(String::length)
                .average()
        );

        Stream.iterate(1, n -> n + 1)
                .limit(100)
                .forEach(value1 -> System.out.println(value1));

        Part part = new Part("Test",1l);
        Optional.of(part.getName())
                .filter(s -> s.equals("Test"))
                .ifPresent(s -> System.out.print(s));
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
