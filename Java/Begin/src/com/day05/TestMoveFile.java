package com.day05;

import java.io.*;

public class TestMoveFile {

    /**
     * 测试使用字节数组形式移动文件
     */
    public void testMove() throws Exception {
        FileInputStream fis = new FileInputStream("fos.dat");
        FileOutputStream fos = new FileOutputStream("src/fos_move.dat");

        int len = -1;
        byte[]  buf = new byte[32];
        while ((len=fis.read(buf)) != -1){
            fos.write(buf,0,len);
        }
        System.out.println("移动完毕");
        fis.close();
        fos.close();

    }

    /**
     * 测试基于缓冲流的复制文件
     * */
    public void testMove2() throws Exception{
        FileInputStream fis = new FileInputStream("fos.dat");
        //创建缓冲字节输入流
        BufferedInputStream bis = new BufferedInputStream(fis);
        FileOutputStream fos = new FileOutputStream("src/bfos.dat");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int d = -1;
        while ((d=bis.read()) != -1){
            bos.write(d);
        }
        System.out.println("移动完毕");
        bis.close();
        bos.close();
    }


}
