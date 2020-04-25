package com.array;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DuplicateElements {
    /**
     * 给定一个整数数组，判断是否存在重复元素。
     * <p>
     * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,3,1]
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: [1,2,3,4]
     * 输出: false
     * 示例 3:
     * <p>
     * 输入: [1,1,1,3,3,4,3,2,4,2]
     * 输出: true
     *
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        //我自己的HashSet方法
//        Set<Integer> set = new HashSet<>();
//        HashSet<Integer> set = new HashSet<>();//直接用子类会更快
//        int len = nums.length;
//        for (int i=0;i<len;i++){
//            if (set.contains(nums[i])){
//                return true;
//            }
//            set.add(nums[i]);
//        }
        //使用for in更快
//        for(int i : nums){
//            if (set.contains(i)) return true;
//            set.add(i);
//        }
//        return false;

        //排序后比较相邻的元素
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (nums[i - 1] == nums[i]) {
                return true;
            }
        }
        return false;


    }
}
