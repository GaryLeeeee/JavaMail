package com.garylee.domain;

import com.garylee.dao.SmailDao;
import com.garylee.util.OutBoxUtil;
import com.garylee.view.login;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class SmailTable extends AbstractTableModel{
    String[] columnNames = new String[] { "id","标题","正文","时间","收件人"};

    public List<Email> emails = new SmailDao().listSmail(login.user);

    public SmailTable(){
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
        Email email = emails.get(rowIndex);
        if (0 == columnIndex)
            return email.getId();
        if (1 == columnIndex)
            return email.getTitle();
        if (2 == columnIndex)
            return email.getContent();
        if (3 == columnIndex)
            return email.getTime();
        if (4 == columnIndex)
            return email.getTo();

        return null;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return emails.size();
    }
}
