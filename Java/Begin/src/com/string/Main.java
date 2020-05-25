package com.string;


import com.day07.TestThread;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};
        ReverseString rs = new ReverseString();
        rs.reverseString(s);
        System.out.println(Arrays.toString(s));
    }
}
