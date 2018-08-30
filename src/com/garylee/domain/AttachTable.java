package com.garylee.domain;

import com.garylee.dao.DraftsDao;
import com.garylee.util.DateUtil;
import com.garylee.view.drafts;
import com.garylee.view.inbox;
import com.garylee.view.login;
import com.garylee.view.lookmail;

import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AttachTable extends AbstractTableModel{
    String[] columnNames = new String[] {"附件列表"};

    public List<Files> strings = new ArrayList<>();
    File file;
    public AttachTable(){
        //获取收件箱的时间戳
        String date = inbox.date;
        date = date.replaceAll(":","-");
        System.out.println(date);
        file = new File("d:\\Users\\Administrator\\Desktop\\attach\\"+ login.user.getUser()+"\\"+date);
        File[] files = file.listFiles();
        //判断是否有该时间戳文件夹
        if(file.exists()) {
            for (File f : files)
                strings.add(new Files(f.getName(), f.getAbsolutePath()));
        }
    }

    public int getColumnCount() {
        // TODO Auto-generated method stub
        return columnNames.length;
    }

    public String getColumnName(int columnIndex) {
        // TODO Auto-generated method stub
        return columnNames[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (0 == columnIndex)
            return strings.get(rowIndex).name;
        return null;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return strings.size();
    }

    public static void main(String[] args) {
        File file = new File("d:\\Users\\Administrator\\Desktop\\attach\\664306561@qq.com\\2018-05-26 19-43-48\\31231.jpg");

    }
}
