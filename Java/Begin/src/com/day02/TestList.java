package com.day02;

import java.lang.reflect.Array;
import java.util.*;

public class TestList {
    /**
     * Comparator接口实现排序ArrayList构建的集合
    * */
    public void testComparatorEmp(){
        List<Emp> emps = new ArrayList<>();
        emps.add(new Emp("Terry",25,'m',6000));
        emps.add(new Emp("smith",23,'m',3000));
        emps.add(new Emp("Allen",21,'f',4000));

        Collections.sort(emps, new Comparator<Emp>() {
            @Override
            public int compare(Emp t1, Emp t2) {
                return t1.salary - t2.salary;
            }
        });
        System.out.println(emps);
        List<String> list = new ArrayList<>();
        list.add("3");
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }
    /**
     * List和数组直接相互转化的方法
     * */
    public void testListAndArrayExchange(){
        //List转数组
        List<String> list = new ArrayList<String>();
        list.add("java");
        list.add("cpp");
        list.add("php");
        list.add("php");
        String[] StrArr =  list.toArray(new String[]{});
        System.out.println(Arrays.toString(StrArr));

        //数组转List
        String[] str = {"a","b","c"};
        List<String> arrToList = Arrays.asList(str);
        System.out.println(arrToList.getClass().getName());
        List<String> list1 = new ArrayList<String>();
        list1.addAll(Arrays.asList(str));
        System.out.println(list1);

    }
    /**
     * Collection集合的iterator遍历 addAll()  remove()
     */
    public void testCollection() {
        Collection<String> c1 = new ArrayList<String>();
        c1.add("java");
        c1.add("cpp");
        c1.add("php");
        c1.add("php");
        Collection<String> c2 = new HashSet<String>();
        c2.addAll(c1);
        System.out.println(c1);
        System.out.println(c2);
        Iterator<String> it = c2.iterator();
        while (it.hasNext()){
            String str = it.next();
            if (str.indexOf('p') != -1){
                it.remove();
            }
        }
        System.out.println(c2);

    }

    /**
     * 测试List的subList方法
     */
    public void testSubList() {
        //1.创建List接口的引用list,使用该引用指向ArrayList的实例；将0~9是个数字作为10个元素放入集合list中，输出一次集合list
        List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);
        //2.获取list集合的子集合subList，subListzi子集合的元素为【3,4,5,6,7】并输出子集合subList
        List subList = list.subList(3, 8);
        System.out.println(subList);
        //3.将subList集合中的每一个元素扩大10倍，输出list集合和subList集合，验证subList获得的List集合和愿List集合占用相同的数据空间
        for (int i = 0; i < subList.size(); i++) {
            subList.set(i, (int) subList.get(i) * 10);
        }
        System.out.println(subList);
        System.out.println(list);
        //清除list集合中索引位置为3~7（包含3和7）的元素，并输出list
        list.subList(3, 7).clear();
        System.out.println(list);
    }

}
