package com.day06;

import java.io.*;

public class TestBrAndPw {

    /**使用BufferedReader和PrintWriter*/
    public void testBrAndPw() throws Exception{
        FileInputStream fis = new FileInputStream("pw.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String line = "";//每行的内容
        String all = "";//每行内容拼接

        while ((line = br.readLine()) != null) {
            all+=line;
            System.out.println(line);
        }
        FileOutputStream os = new FileOutputStream("pw.txt",true);
        PrintWriter pw = new PrintWriter(os,true);
        System.out.println(all);
        pw.println();
        pw.println(all);
        br.close();
        pw.close();
    }
}