package com.day03;

import java.util.*;

public class TestHashMap {
    /**
     * 统计一句话中各个字符的个数
     */
    public void countNums() {
        String str = "good good study,day day up.";//1111
        String newstr = str.replaceAll("[\\s*,*.*]", "");
        String[] arr = newstr.split("");
        System.out.println(Arrays.toString(arr));
        HashMap<String, Integer> map = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {//如果key存在，则value+1，计数重复次数
                int nums = map.get(arr[i]) + 1;
                map.put(arr[i], nums);
            } else {
                map.put(arr[i], 1);
            }
        }
        System.out.println(map);

    }

    /**
     * 统计一句话中的单词数
     */
    public void countWords() {
        String str = "good good study,day day up.";//1111
        String newStr = str.replaceAll("[\\s+,*.*]", " ");
        String[] words = newStr.split(" ");
        //将字符串数组循环存入队列
        Queue<String> queue = new LinkedList<>();
        for (String key : words) {
            queue.offer(key);
        }
        //LinkedHashMap可按顺序循环输出
        HashMap<String, Integer> map = new LinkedHashMap<>();
        while (queue.size() > 0) {
            String key = queue.poll();
            if (!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                map.put(key, map.get(key) + 1);
            }
        }

        //循环keyset
        Set<String> keyset = map.keySet();
        for (String setwords : keyset) {
            String word = setwords;
            System.out.println(word + "重复次数：" + map.get(word));
        }
        System.out.println("");

        //keyset的iterator遍历
        Iterator<String> it = keyset.iterator();
        while (it.hasNext()) {
            String word = it.next();
            System.out.println(word + "重复次数：" + map.get(word));
        }
        System.out.println("");

        //循环entry
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + "重复次数" + value);
        }
        System.out.println("");

        //entry的iterator遍历
        Iterator<Map.Entry<String,Integer>> entryIt = entrySet.iterator();
        while (entryIt.hasNext()){
            Map.Entry<String,Integer> tmpEntry  = entryIt.next();
            String word = tmpEntry .getKey();
            Integer num = tmpEntry .getValue();
            System.out.println(word + "重复次数" + num);
        }
    }
}
