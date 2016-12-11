package com.arun.singh.java.features.test.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RuducingExample {

    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();

        final Employee employee1 = new Employee(25, 1000, "test", null);
        System.out.println(employee1.<String, String>getDetail(""));


        final List<Employee> subordinates = new ArrayList<Employee>();
        subordinates.add(new Employee(50, 5000, "Robin", null));
        list.add(new Employee(20, 1000, "Rob", subordinates));
        list.add(new Employee(30, 1000, "Bean", subordinates));
        list.add(new Employee(40, 3000, "Tess", subordinates));
        list.add(new Employee(50, 5000, "Ruby", subordinates));
// reduce must return same type it received
        System.out.println(list.stream().map(Employee::getAge).reduce((a, b) -> a + b).orElse(0));

// Collector can return different type using grouping logic
        System.out.println(list.stream().map(employee -> employee.getAge()).collect(Collectors.toList()));
        System.out.println(list.stream().flatMap(employee -> employee.getSubordinate().stream()).map(Employee::getName).collect(Collectors.toList()));
        System.out.println(list.stream().collect(Collectors.summingInt(value -> value.getAge())));
        System.out.println(list.stream().collect(Collectors.groupingBy(Employee::getSalary)));

    }
}

class Employee {

    private final int age;
    private final int salary;
    private final String name;
    private final List<Employee> Subordinate;

    Employee(int age, int salary, String name, List<Employee> subordinate) {
        this.salary = salary;
        this.age = age;
        this.name = name;
        Subordinate = subordinate;
    }

    public List<Employee> getSubordinate() {
        return Subordinate;
    }


    public <T,P>  P getDetail(T t){
        return null;
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
