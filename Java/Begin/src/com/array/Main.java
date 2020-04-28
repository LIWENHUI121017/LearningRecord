package com.array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

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

//        int[] arr = {5, 2, 3, 7, 6, 4, 5};
//        DuplicateElements de = new DuplicateElements();
//        System.out.println(de.containsDuplicate(arr));

//        int[] arr = {1,1,2,3,3,4,2};
//        SingleNumber sn = new SingleNumber();
//        System.out.println(sn.singleNumber(arr));

//        int[]nums1 = {1,2,2,1,2}, nums2 = {2,2,2};
//        Intersect is = new Intersect();
//        System.out.println(Arrays.toString(is.intersect(nums1,nums2)));
//

//        int[] nums = {9,9,9};
//        PlusOne po = new PlusOne();
//        System.out.println(Arrays.toString(po.plusOne(nums)));

//        int[] nums = {2,1};
//        MoveZeroes mz = new MoveZeroes();
//        mz.moveZeroes(nums);
//        System.out.println(Arrays.toString(nums));

        int[] nums = {9,6,3,3,2,1};
        TwoSum ts = new TwoSum();
        int[] res = ts.twoSum(nums,3);
        System.out.println(Arrays.toString(res));
    }


}
