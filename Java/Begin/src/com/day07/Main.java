package com.day07;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Thread t1 = new TestThread();
        Thread t2 = new TestThread();
        t1.start();
        t2.start();
    }
}
