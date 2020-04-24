package com.array;


import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2};
        RemoveDuplicates rd = new RemoveDuplicates();
        System.out.println(rd.removeDuplicates(arr));
        System.out.println(arr.toString());
    }


}
