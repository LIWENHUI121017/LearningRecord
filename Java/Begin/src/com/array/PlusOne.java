package com.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlusOne {
    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * <p>
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * <p>
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,3]
     * 输出: [1,2,4]
     * 解释: 输入数组表示数字 123。
     * 示例 2:
     * <p>
     * 输入: [4,3,2,9]
     * 输出: [4,3,3,0]
     * 解释: 输入数组表示数字 4330。
     */
    public int[] plusOne(int[] digits) {
        //这道题就相当于一个会加一的指针正在倒过来扫描数组，每一个位置都加1一次，如果余数等于0，就继续往下执行，等同于逢十进一
        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;//如果是9，加一后就是10，除余后就是0，说明前一位数也要执行自加
            if (digits[i] != 0) return digits;
        }
        //如果像999,9999……这种，数组最后要加一位
        digits = new int[len + 1];
        System.out.println(Arrays.toString(digits));
        digits[0] = 1;
        return digits;
    }
}
