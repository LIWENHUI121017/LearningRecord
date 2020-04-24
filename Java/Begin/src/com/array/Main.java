package com.array;


import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
//        int[] arr = {1, 1, 2};
//        RemoveDuplicates rd = new RemoveDuplicates();
//        System.out.println(rd.removeDuplicates(arr));
//        System.out.println(arr.toString());
        SellStocks ss = new SellStocks();
        int[] arr = {7,1,5,3,6,4};
        System.out.println(ss.maxProfit(arr));
    }


}
