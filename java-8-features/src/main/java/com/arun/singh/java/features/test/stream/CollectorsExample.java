package com.arun.singh.java.features.test.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Arun on 4/17/2016.
 */
public class CollectorsExample {

    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        list.add("Abc");
        list.add("AXyz");

        List<String> l = new ArrayList(Arrays.asList("one", "two"));
        System.out.println(l.stream().collect(java.util.stream.Collectors.joining("")));




    }
}
