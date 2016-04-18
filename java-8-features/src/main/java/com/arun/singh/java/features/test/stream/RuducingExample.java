package com.arun.singh.java.features.test.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Arun on 4/17/2016.
 */
public class RuducingExample {

    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();

        list.add(new Employee(20, 1000, "Rob"));
        list.add(new Employee(30, 1000, "Bean"));
        list.add(new Employee(40, 3000, "Tess"));
        list.add(new Employee(50, 5000, "Ruby"));
// reruce must return same type it received
        System.out.println(list.stream().map(Employee::getAge).reduce((a, b) -> a + b).orElse(0));

// Collector can return different type using grouping logic
        System.out.println(list.stream().map(employee -> employee.getAge()).collect(Collectors.toList()));
        System.out.println(list.stream().collect(Collectors.summingInt(value -> value.getAge())));
        System.out.println(list.stream().collect(Collectors.groupingBy(Employee::getSalary)));

    }
}

class Employee {

    private final int age;
    private final int salary;
    private final String name;

    Employee(int age, int salary, String name) {
        this.salary = salary;
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }
}
