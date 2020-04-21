package com.day04;


import com.day04.TestFile;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TestFile testFile = new TestFile();
        testFile.testCreateNewFile();
//        testFile.testDeleteFile();
//        System.out.println("删除状态" + testFile.testDeleteDir(new File("myDir")));
//        testFile.testMkdir();
//        testFile.testCheckLength();
    }
}
