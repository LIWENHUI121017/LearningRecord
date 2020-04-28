package com.array;

import java.util.Arrays;

public class MoveZeroes {
    /**
     *  移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 示例:
     *
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     *
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     *
     * @return
     */
    public void moveZeroes(int[] nums) {
//        //双指针的两种使用方法
//        //1.把所有不为0的数字全部放到前面，然后后面位置全部换成0
//        int len = nums.length;
//        int j = 0;
//        for (int i=0;i<len;i++){
//           if (nums[i] != 0){
//               nums[j++] = nums[i];
//           }
//        }
//        for (int i=j;i<len;i++){
//            nums[i] =0;
//        }
        //2.只需循环一次，类似快速排序，把不为0的放左边，等于0的放右边
        int len = nums.length;
        int j = 0;
        if (nums == null || len <=0){
            return;
        }
        for (int i=0;i<len;i++){
            if (nums[i]!=0){
                if (i > j) {
                    //增加i>j判断，避免i=j的时候重复执行
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
                j++;
            }
        }


    }
}
