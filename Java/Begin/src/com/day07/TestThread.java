package com.day07;

import java.io.PrintWriter;

public class TestThread extends Thread {
    /**测试多线程并发运行*/
    @Override
    public void run() {
        for (int i=1;i<=100;i++){
            System.out.println(i);
        }
    }
}