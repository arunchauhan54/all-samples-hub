package com.arun.singh.java.features.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        list.add("Abc");
        list.add("AXyz");
        System.out.println(list.stream()
                .filter(string -> string.startsWith("A"))
                .map(String::length)
                .reduce((int1, int2) -> int1+int2)
        );

        Stream.iterate(1, n -> n + 1)
                .limit(10)
                .forEach(System.out::println);

        Part part = new Part("Test",1l);
        Optional.of(part.getName())
                .filter(s -> s.equals("Test"))
                .ifPresent(s -> System.out.print(s));



        List<String> l = new ArrayList(Arrays.asList("one", "two"));
        l.stream().collect(Collectors.joining(""));

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
