package com.garylee.domain;

import com.garylee.dao.EmailDao;
import com.garylee.util.OutBoxUtil;
import com.garylee.view.login;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.List;

public class EmailTable extends AbstractTableModel{
    String[] columnNames = new String[] { "id","title","content","time","from"};

//    public List<Email> emails = new OutBoxUtil(login.user).getEmails();
    public static List<Email> emails = new EmailDao().list(login.user);

    public EmailTable(){
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
        Email course = emails.get(rowIndex);
        if (0 == columnIndex)
            return course.getId();
        if (1 == columnIndex)
            return course.getTitle();
        if (2 == columnIndex)
            return course.getContent();
        if (3 == columnIndex)
            return course.getTime();
        if (4 == columnIndex)
            return course.getFrom();
        return null;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return emails.size();
    }

}
