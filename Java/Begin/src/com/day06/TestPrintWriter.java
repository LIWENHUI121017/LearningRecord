package com.day06;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TestPrintWriter {

    /**
     * 测试缓冲字符流输出
     */
    public void testPrintWriter() throws Exception {
        PrintWriter pw = new PrintWriter("pw.txt");
        pw.println("大家好");
        pw.println("hello");
        pw.println("bye");
        pw.close();
    }

    /**
     * 测试缓冲字符输入流
     */
    public void testBufferedReader() {

    }
}
