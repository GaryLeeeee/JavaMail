package com.garylee.domain;

import com.garylee.dao.UserDao;
import com.garylee.util.OutBoxUtil;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTable extends AbstractTableModel{
    String[] columnNames = new String[] { "id","name","password"};

    public List<User> users = new UserDao().listUser();

    public UserTable(){
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
        User user = users.get(rowIndex);
        if (0 == columnIndex)
            return user.getId();
        if (1 == columnIndex)
            return user.getUser();
        if (2 == columnIndex)
            return user.getUpassword();

        return null;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return users.size();
    }
}
