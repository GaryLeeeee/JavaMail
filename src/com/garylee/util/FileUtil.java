package com.garylee.util;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FileUtil {
    /***
     * 删除指定文件夹下所有文件
     *
     * @param path 文件夹完整绝对路径
     * @return
     */
    public static  boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    public  static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //打开文件
    public static void openFile(File file){
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
//        delFolder("d:\\Users\\Administrator\\Desktop\\attach\\664306561@qq.com - 副本");
        File file = new File("d:\\Users\\Administrator\\Desktop\\attach\\664306561@qq.com\\2018-05-26 19-43-48\\31231.jpg");
        String filepath = file.getAbsolutePath();
        try {
            Desktop.getDesktop().open(file);  //打开的文件带空格
        } catch (IOException e) {
            System.out.println("打开文件出错");
        }
    }
}
