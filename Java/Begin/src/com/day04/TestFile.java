package com.day04;

import java.io.File;
import java.io.IOException;

public class TestFile {

    /**
     * 创建文件
     */
    public void testCreateNewFile() throws IOException {
        File file = new File( "fos.dat");
        //若不存在就创建
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * 创建多个文件夹
     */
    public void testMkdir() {
//        File dir = new File("myDir");
        File dir = new File("myDir" + File.separator + "a" + File.separator + "b" + File.separator + "c");
//        dir.mkdir();
        dir.mkdirs();
    }

    /**
     * 查看文件和文件目录大小
     */
    public void testCheckLength() {
        File file = new File("demo" + File.separator + "Hello.txt");
        File directory = new File("demo" + "../" + "src");
        System.out.println("文件大小" + file.length());
        System.out.println("项目src的大小" + directory.length());
    }

    /**
     * 删除文件
     */
    public void testDeleteFile() {
        File file = new File("demo" + File.separator + "Hello.txt");
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 删除文件目录
     * @param dir 目录
     * @return
     */
    public boolean testDeleteDir(File dir) {
        if (dir.isDirectory()) {
            //循环递归删除该目录下的所有子目录
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                File subDir = new File(dir,children[i]);
                testDeleteDir(subDir);
            }
            return dir.delete();
        }
        return false;
    }
}
