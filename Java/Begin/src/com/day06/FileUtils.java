package com.day06;

import java.io.*;

public class FileUtils {

    /**
     * 对复制文件的代码添加异常捕获机制
     */
    public void testCopy() throws CopyException{
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
           throw new CopyException("文件没找到",e);
        } catch (IOException e) {
//            System.err.println("读写异常");
            throw new CopyException("读写异常",e);
//            e.printStackTrace();
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
