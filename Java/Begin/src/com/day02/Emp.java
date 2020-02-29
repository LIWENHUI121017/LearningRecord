package com.day02;

public class Emp {
    public String name;//姓名
    public Integer age;//年龄
    public char gender;//性别
    public Integer salary;//工资

    public Emp(String name, int age, char gender, int salary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "[" +
                name +
                ", " + age +
                ", " + gender +
                ", " + salary +
                ']';
    }
}
