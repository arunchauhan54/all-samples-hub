package com.arun.singh.java.features.test.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arun on 4/17/2016.
 */
public class Limit {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Abc");
        list.add("AXyz");
        java.util.stream.Stream.iterate(1, n -> n + 1)
                .limit(10)
                .forEach(System.out::println);
    }
}
