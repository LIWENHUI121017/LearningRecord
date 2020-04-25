package com.array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
//        int[] arr = {1, 1, 2};
//        RemoveDuplicates rd = new RemoveDuplicates();
//        System.out.println(rd.removeDuplicates(arr));
//        System.out.println(arr.toString());
//        SellStocks ss = new SellStocks();
//        int[] arr = {7,1,5,3,6,4};
//        System.out.println(ss.maxProfit(arr));
//        int[] arr = {1,2,3,4,5,6,7};
//        int k =5;
//        RotateArray ra = new RotateArray();
//        ra.rotate(arr, k);
//        System.out.println(Arrays.toString(arr));

        int[] arr = {5, 2, 3, 7, 6, 4, 5};
        DuplicateElements de = new DuplicateElements();
        System.out.println(de.containsDuplicate(arr));
    }


}
