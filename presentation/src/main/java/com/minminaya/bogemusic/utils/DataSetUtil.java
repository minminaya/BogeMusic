package com.minminaya.bogemusic.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Niwa 2017年6月2日
 * 这个是关于数据相关的工具类，例如list保存成本地文件
 * Created by Niwa on 2017/6/1.
 */

public class DataSetUtil {

    /**
     *
     * 1.先建立目录
     * 2.接着建立文件，文件名建议用对象名加上seria后缀，比如要保存list对象，文件名是list.seria，代表list序列化后保存的文件
     *
     * @param object   要保存为文件的目标对象
     * @param filePath 保存文件的路径，假如没有就创建一个
     * @param fileName 保存文件的文件名
     */
    public static void writeObject(Object object, String filePath, String fileName) {

        File fileDirsPath = new File(filePath);
        if (!fileDirsPath.exists()) {
            fileDirsPath.mkdirs();//如果目录不存在，则创建目录
        }

        File file = new File(fileDirsPath, fileName);
        if (!file.exists()) {

            try {
                file.createNewFile();//如果没有该文件，则创建新文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file.delete();//如果该文件存在则删除文件
            try {
                System.out.println(fileName + "文件" + "已存在" + "-------->" + "准备重新建立文件");
                file.createNewFile();//重新建立文件
            } catch (IOException e) {
                e.printStackTrace();
            }
            ;
        }


        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取保存的对象文件
     *
     * @param filePath 要读取的目标文件的位置
     * @param fileName 目标文件名
     */
    public static Object readObject(String filePath, String fileName) {
        String path = filePath + "\\" + fileName;

        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        Object object = null;

        try {
            fileInputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            objectInputStream = new ObjectInputStream(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            object = objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return object;

    }
}
