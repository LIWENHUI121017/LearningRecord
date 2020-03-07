package com.day03;

import java.util.Arrays;
import java.util.HashMap;

public class TestHashMap {
    /**
     * 统计一句话中各个字符的个数
     * */
    public void countNums() {
        String str = "good good study,day day up.";//1111
        String newstr = str.replaceAll("[\\s*,*.*]", "");
        String[] arr = newstr.split("");
        System.out.println(Arrays.toString(arr));
        HashMap<String, Integer> map = new HashMap();
        for (int i=0;i<arr.length;i++){
           if (map.containsKey(arr[i])){//如果key存在，则value+1，计数重复次数
                int nums = map.get(arr[i]) + 1;
                map.put(arr[i],nums);
           }else{
               map.put(arr[i],1);
           }
        }
        System.out.println(map);

    }
}
