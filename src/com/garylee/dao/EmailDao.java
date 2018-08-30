package com.garylee.dao;

import com.garylee.domain.Draft;
import com.garylee.domain.Email;
import com.garylee.domain.User;
import com.garylee.util.DateUtil;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailDao {
    public List<Email> list(User user){
        List<Email> emails = new ArrayList<>();
        String sql = "select * from email where email.to = '"+user.getUser()+"' order by time desc";
        try (Connection c = Jdbc.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Email email = new Email();
                email.setId(resultSet.getInt(1));
                email.setTitle(resultSet.getString(2));
                email.setContent(resultSet.getString(3));
                email.setTime(resultSet.getString(4));
                email.setFrom(resultSet.getString(5));
                emails.add(email);
            }
            return emails;
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }
    public void add(Email email) throws ParseException {

        String sql = "insert into email values(null,?,?,?,?,?)";
        try (Connection c = Jdbc.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, email.getTitle());
            ps.setString(2, email.getContent());
            ps.setString(3, email.getTime());
//            ps.setDate(3, new java.sql.Date(new Date().getTime()));
            ps.setString(4, email.getFrom());
            ps.setString(5, email.getTo());

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public int hasReceived(){
        String sql = "select count(*) from email";
        int num = -1;
        try (Connection c = Jdbc.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                num = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    public void delEmail(int id){

        String sql = "delete from email where id = ?";
        try (Connection c = Jdbc.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public Email getOne(int id){
        String sql = "select * from email where id = " + id;
        try (Connection c = Jdbc.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                Email email = new Email();
                email.setId(resultSet.getInt(1));
                email.setTitle(resultSet.getString(2));
                email.setContent(resultSet.getString(3));
                email.setTime(resultSet.getString(4));
                email.setFrom(resultSet.getString(5));
                email.setTo(resultSet.getString(6));
                return email;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(new EmailDao().getOne(795));
    }
}
