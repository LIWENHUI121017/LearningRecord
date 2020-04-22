package com.day05;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现对象序列化与反序列化
 */
public class TestSerialEmps {

    /**
     * 使用OOS实现对象的序列化
     */
    public void testWriter()throws Exception {
        FileOutputStream fos = new FileOutputStream("emplist.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        List<Emp> emps = new ArrayList<Emp>();
        emps.add(new Emp("张三",33,"男",9000));
        emps.add(new Emp("李四",26,"男",5000));
        emps.add(new Emp("王五",48,"男",33000));
        oos.writeObject(emps);
        System.out.println("序列化完毕");
//        System.out.println(emps);
        oos.close();
    }

    public void testRead() throws Exception{
        FileInputStream fis = new FileInputStream("emplist.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        List emps = (List)ois.readObject();
        System.out.println("反序列化完毕");
        System.out.println(emps);
        ois.close();
    }
}
