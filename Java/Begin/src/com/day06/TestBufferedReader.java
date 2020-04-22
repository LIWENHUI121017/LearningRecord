package com.day06;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TestBufferedReader {

    /**
     * 测试缓冲字符输入流
     */
    public void testBufferedReader() throws Exception {
        FileInputStream fis = new FileInputStream("pw.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

}
