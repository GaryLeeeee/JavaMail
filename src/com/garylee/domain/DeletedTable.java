package com.garylee.domain;

import com.garylee.dao.DeletedDao;
import com.garylee.view.drafts;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DeletedTable extends AbstractTableModel{
    String[] columnNames = new String[] {"id","标题","正文","时间","发件人","收件人","类型"};

    public List<Deleted> deleteds = new DeletedDao().listDeleted();

    public DeletedTable(){
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
        Deleted deleted = deleteds.get(rowIndex);
        if (0 == columnIndex)
            return deleted.getId();
        if (1 == columnIndex)
            return deleted.getTitle();
        if (2 == columnIndex)
            return deleted.getContent();
        if (3 == columnIndex)
            return deleted.getTime();
        if (4 == columnIndex)
            return deleted.getFrom();
        if (5 == columnIndex)
            return deleted.getTo();
        if (6 == columnIndex)
            return deleted.getType();

        return null;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return deleteds.size();
    }
}
