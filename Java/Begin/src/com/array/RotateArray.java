package com.array;

public class RotateArray {
    /**
     * 旋转数组
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     * <p>
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     * 说明:
     * <p>
     * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
     * 要求使用空间复杂度为 O(1) 的 原地 算法。
     */
    public void rotate(int[] nums, int k) {
        //暴力解法，直接一步步交换数字
//        int temp, previous;
//        for (int i = 0; i < k; i++) {
//            previous = nums[nums.length - 1];
//            //每次都拿数组最后一个数字塞到数组最前面，然后挤掉第一个数组往后面走，就像换座位一样，要求最后一位的同学坐到第一排，然后第一位的同学只能往后退一位，接着第二位的同学也得跟着后退一位
//            for (int j = 0; j < nums.length; j++) {
//                temp = nums[j];
//                nums[j] = previous;
//                previous = temp;
//            }
//        }

        //环状解法，用余数决定下一个位置在哪，最终会回到原来的位置，将被替换的数据存起来，与下一个位置的数进行替换，直到回到原来的位置
//        k = k % nums.length;
//        int count = 0;
//        for (int start = 0; count < nums.length; start++) {
//            int current = start;//当前位置
//            int prev = nums[start];//当前位置的数字
//            do {
//                int next = (current + k) % nums.length;
//                int temp = nums[next];
//                nums[next] = prev;
//                current = next;
//                prev = temp;//将被挤出来的数存在prev，然后跳到下一个位置的时候，放进去
//                count++;//已经转换了位置的元素的数量，如果count = nums.length的时候，说明已经全部元素换好位置了，现在这个是跟着余数隔着位置换位置的
//            } while (current != start);//当前位置再次等于初始位置的时候，跳出循环
//        }

        //反转法
        //当我们旋转数组 k 次， k%n 个尾部元素会被移动到头部
        //例如：1,2,3,4,5,6,7 k=3
        //反转后是 ：5,6,7,1,2,3,4
        //等价于：7,6,5,4,3,2,1  前3位反转(k) 后四位反转(nums.length - k)
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length - 1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
            start++;
            end--;
        }
    }
}
