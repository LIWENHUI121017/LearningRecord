package com.day05;


import java.io.Serializable;

/**实现*/
public class Emp implements Serializable {

    /**
     * 实现序列化接口后该类可以被序列化
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private String gender;
    private double salary;

    public Emp(String name, int age,String gender,double salary){
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.salary=salary;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public String getGender(){
        return gender;
    }
    public double getSalary(){
        return salary;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setAge(int age){
        this.age=age;
    }
    public void setGender(String gender){
        this.gender=gender;
    }
    public void setSalary(double salary){
        this.salary=salary;
    }

    public String toString(){
        return "Emp [name=" + name +", age="+age+", gender="+gender+", salary="+salary+"]";
    }
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name==null) ? 0: name.hashCode());
        return result;
    }
}
