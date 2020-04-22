package com.day06;

import java.io.*;

public class FileUtils {

    /**
     * 对复制文件的代码添加异常捕获机制
     */
    public void testCopy() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("fos.dat");
            fos = new FileOutputStream("fos_copy4.dat");
            int d = -1;
            while ((d = fis.read()) != -1) {
                fos.write(d);
            }
            System.out.println("复制完毕");
        } catch (FileNotFoundException e) {
            System.err.println("文件没有找到");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("读写异常");
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (fos != null){
                try {
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }


    }
}
