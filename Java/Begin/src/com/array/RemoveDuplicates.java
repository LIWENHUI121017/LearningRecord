package com.array;

import java.util.ArrayList;
import java.util.HashMap;

public class RemoveDuplicates {
    /**
     * 每日算法
     * 删除排序数组中的重复项
     * <p>
     * 示例一：
     * 给定数组 nums = [1,1,2],
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     * 你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 示例二：
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     * 你不需要考虑数组中超出新长度后面的元素。
     */
    public int removeDuplicates(int[] nums) {
//        int len = nums.length;
//        ArrayList<Integer> Arr = new ArrayList<>();
//        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//        for (int i = 0; i < len; i++) {
//            //如果hashmap里面有标记，说明是重复的
//            if (map.containsKey(nums[i]) == false) {
//                System.out.println(nums[i]);
//                Arr.add(nums[i]);
//                map.put(nums[i], 1);
//            } else {
//                map.put(nums[i], 1);
//            }
//        }
//        for (int j = 0; j < Arr.size(); j++) {
//            nums[j] = Arr.get(j);
//        }
//        return Arr.size();

        if (nums.length == 0) return 0;
        int len = nums.length;
        int i = 0;
        for (int j = 1; j < len; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1;
    }
}
