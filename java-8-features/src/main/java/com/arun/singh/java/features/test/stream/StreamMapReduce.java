package com.arun.singh.java.features.test.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMapReduce {

    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        list.add("Abc");
        list.add("AXyz");
        System.out.println(list.stream()
                .filter(string -> string.startsWith("A"))
                .map(String::length)
                .reduce((int1, int2) -> int1 + int2)
        );

        java.util.stream.Stream.iterate(1, n -> n + 1)
                .limit(10)
                .forEach(System.out::println);

        List<String> l = new ArrayList(Arrays.asList("one", "two"));
        System.out.println(l.stream().collect(Collectors.joining("")));

    }
}


