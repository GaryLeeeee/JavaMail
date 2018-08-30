package com.garylee.dao;

import com.garylee.domain.Deleted;
import com.garylee.domain.Draft;
import com.garylee.domain.Email;
import com.garylee.view.drafts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeletedDao {
    public void addDraft(Draft draft){

        String sql = "INSERT INTO deleted values (null,?,?,?,?,?,?)";
        try (Connection c = Jdbc.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1,draft.getTitle());
            ps.setString(2,draft.getContent());
            ps.setString(3,"");
            ps.setString(4,draft.getFrom());
            ps.setString(5,draft.getTo());
            ps.setString(6,"草稿箱");

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public void addReceive(Email email){

        String sql = "INSERT INTO deleted values (null,?,?,?,?,?,?)";
        try (Connection c = Jdbc.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1,email.getTitle());
            ps.setString(2,email.getContent());
            ps.setString(3,email.getTime());
            ps.setString(4,email.getFrom());
            ps.setString(5,email.getTo());
            ps.setString(6,"收件箱");

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public void addSend(Email email){

        String sql = "INSERT INTO deleted values (null,?,?,?,?,?,?)";
        try (Connection c = Jdbc.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1,email.getTitle());
            ps.setString(2,email.getContent());
            ps.setString(3,email.getTime());
            ps.setString(4,email.getFrom());
            ps.setString(5,email.getTo());
            ps.setString(6,"已发送");

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public List<Deleted> listDeleted(){
        List<Deleted> deleteds = new ArrayList<>();
        String sql = "select * from deleted";
        try (Connection c = Jdbc.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Deleted deleted = new Deleted();
                deleted.setId(resultSet.getInt(1));
                deleted.setTitle(resultSet.getString(2));
                deleted.setContent(resultSet.getString(3));
                deleted.setTime(resultSet.getString(4));
                deleted.setFrom(resultSet.getString(5));
                deleted.setTo(resultSet.getString(6));
                deleted.setType(resultSet.getString(7));
                deleteds.add(deleted);
            }
            return deleteds;
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }
    public void delDeleted(int id){

        String sql = "delete from deleted where id = ?";
        try (Connection c = Jdbc.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public Deleted getOne(int id){
        String sql = "select * from deleted where id = " + id;
        try (Connection c = Jdbc.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                Deleted deleted = new Deleted();
                deleted.setId(resultSet.getInt(1));
                deleted.setTitle(resultSet.getString(2));
                deleted.setContent(resultSet.getString(3));
                deleted.setTime(resultSet.getString(4));
                deleted.setFrom(resultSet.getString(5));
                deleted.setTo(resultSet.getString(6));
                deleted.setType(resultSet.getString(7));
                return deleted;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }
}
