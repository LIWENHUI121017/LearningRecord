package com.day01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ParseException {
        // write your code here
//        Collection<Cell> cells = new ArrayList<Cell>();
//        cells.add(new Cell(1,2));
//        cells.add(new Cell(1,3));
        getDate();
    }

    /**
     * 商品促销日期计算
     * 到保质期前14天所在的周的周三为促销日
     */
    public static void getDate() throws ParseException {
        System.out.println("请输入日期（yyyy-MM-dd）");
        Scanner scanner = new Scanner(System.in);
        String dateStr = scanner.next();
        System.out.println("请输入保质期（天数）");
        int days = scanner.nextInt() - 14;//到保质期前14天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //将用户输入的字符串转化为Date对象
        Date date = sdf.parse(dateStr);
        //日历对象，Calendar用来计算保质期前的14天的周的周三是什么日期
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);//保质期前的14天
        c.set(Calendar.DAY_OF_WEEK,4);//设置为星期三的日期，星期日是1 所以星期三是4
        System.out.println("促销日为：" + sdf.format(c.getTime()));
    }
}
