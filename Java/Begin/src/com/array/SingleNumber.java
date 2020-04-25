package com.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class SingleNumber {
    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * <p>
     * 说明：
     * <p>
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * <p>
     * 示例 1:
     * <p>
     * 输入: [2,2,1]
     * 输出: 1
     * 示例 2:
     * <p>
     * 输入: [4,1,2,1,2]
     * 输出: 4
     */
    public int singleNumber(int[] nums) {
        //使用HashMap
//        HashMap<Integer, Integer> map = new HashMap();
//        for (int i : nums) {
//            map.put(i, map.getOrDefault(i, 0) + 1);
//        }
//        for (int j:nums){
//            if (map.get(j) ==1)return j;
//        }
//        return 0;

        //数学方法，{1,1,2}假设全部都是两个，那2*(1+2) - (1+1+2) = 2,2就是那个单独的数字
//        HashSet<Integer> set = new HashSet<>();
//        int sumOfSet = 0, sumOfNums = 0;
//        for (int num : nums) {
//            if (!set.contains(num)) {
//                set.add(num);
//                sumOfSet += num;//Set不能存重复的数字，所以相当于把数组全部去重后加起来的结果
//            }
//            sumOfNums += num;//数组全部数字加起来
//        }
//        return 2 * sumOfSet - sumOfNums;

        //        int[] arr = {1,1,2,3,3,4,2};
        int a = 0;
        for (int num : nums){
            a ^= num;
        }
        return a;
    }
}
