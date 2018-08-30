package com.garylee.dao;

import com.garylee.domain.Draft;
import com.garylee.domain.Email;
import com.garylee.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DraftsDao {
    public void addDrafts(Draft draft){

        String sql = "insert into draft values(null,?,?,?,?)";
        try (Connection c = Jdbc.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, draft.getFrom());
            ps.setString(2, draft.getTo());
            ps.setString(3, draft.getTitle());
            ps.setString(4, draft.getContent());

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public List<Draft> listDrafts(){
        List<Draft> drafts = new ArrayList<>();
        String sql = "select * from draft";
        try (Connection c = Jdbc.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Draft draft = new Draft();
                draft.setId(resultSet.getInt(1));
                draft.setFrom(resultSet.getString(2));
                draft.setTo(resultSet.getString(3));
                draft.setTitle(resultSet.getString(4));
                draft.setContent(resultSet.getString(5));
                drafts.add(draft);
            }
            return drafts;
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }

    public void delDraft(int id){

        String sql = "delete from draft where id = ?";
        try (Connection c = Jdbc.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public Draft getOne(int id){
            String sql = "select * from draft where id = " + id;
            try (Connection c = Jdbc.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next()){
                    Draft draft = new Draft();
                    draft.setId(resultSet.getInt(1));
                    draft.setFrom(resultSet.getString(2));
                    draft.setTo(resultSet.getString(3));
                    draft.setTitle(resultSet.getString(4));
                    draft.setContent(resultSet.getString(5));
                    return draft;
                }
            } catch (SQLException e) {

                e.printStackTrace();
            }
            return null;
    }
    public static void main(String[] args) {
        List<Draft> drafts = new DraftsDao().listDrafts();

            System.out.println(drafts.size());
    }
}
