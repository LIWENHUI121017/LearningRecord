package com.day06;

public class TestRuntimeException {
    public void testNullPointerException() {
        String str = null;
        //会发生空指针异常
        System.out.println(str.length());
    }

    /**
     * 测试数组下标越界异常
     */
    public void testArrayIndexOutOfBoundsException() {
        byte[] bytes = "hello".getBytes();
        System.out.println(bytes);
    }

    /**
     * 测试数学异常
     */
    public void testArithmeticException() {
        System.out.println(5 / 0);
    }

    /**
     * 测试强制类型转换异常
     */
    public void testClassCastException() {
        Object obj = "hello";
        Integer i = (Integer)obj;
    }

    /**
     * 测试NumberFormat异常
     */
    public void testNumberFormatException() {
        int num = Integer.parseInt("a");
    }
}
