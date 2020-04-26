package com.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Intersect {
    /**
     * 两个数组的交集 II
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2,2]
     * 示例 2:
     * <p>
     * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出: [4,9]
     * 说明：
     * <p>
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
     * 我们可以不考虑输出结果的顺序。
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        //排序后，双指针扫描两个数组
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, k = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        while (i < len1 && j < len2) {
            if (nums1[i] < nums2[j]) {
                ++i;
            } else if (nums1[i] > nums2[j]) {
                ++j;
            } else {
                nums1[k++] = nums1[i++];
                ++j;
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
//        HashMap<Integer, Integer> map = new HashMap<>();
//        ArrayList<Integer> arr = new ArrayList<>();
//        int len1 = nums1.length;
//        int len2 = nums2.length;
//
//        if (len1 > len2) {
//            for (int i = 0; i < len2; i++) {
//                map.put(nums2[i], map.getOrDefault(nums2[i], 0) + 1);
//            }
//            for (int j = 0; j < len1; j++) {
//                if (map.containsKey(nums1[j]) && map.get(nums1[j]) > 0) {
//                    arr.add(nums1[j]);
//                    map.put(nums1[j], map.get(nums1[j]) - 1);
//                }
//            }
//
//            int length = arr.size();
//            int[] res = new int[length];
//            for (int i = 0; i < length; i++) {
//                res[i] = arr.get(i);
//            }
//            return res;
//        } else {
//            for (int i = 0; i < len1; i++) {
//                map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
//            }
//            for (int j = 0; j < len2; j++) {
//                if (map.containsKey(nums2[j]) && map.get(nums2[j]) > 0) {
//                    arr.add(nums2[j]);
//                    map.put(nums2[j], map.get(nums2[j]) - 1);
//                }
//            }
//
//            int length = arr.size();
//            int[] res = new int[length];
//            for (int i = 0; i < length; i++) {
//                res[i] = arr.get(i);
//            }
//            return res;
//        }


    }
}
