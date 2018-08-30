package com.garylee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.garylee.domain.User;

public class UserDao {
	public User getOne(String name, String password){
		String sql = "select * from user where user = ? and upassword = ?";
		try (Connection c = Jdbc.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()){
				return new User(name, password);
			}
				
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
		return null;
	}
	public boolean getAdmin(String name, String password){
		String sql = "select * from admin where admin = ? and apassword = ?";
		try (Connection c = Jdbc.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public void add(User user){
		String sql = "insert into user values (?,?)";
		try (Connection c = Jdbc.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, user.getUser());
			ps.setString(2, user.getUpassword());
			ps.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	public void update(User user){
		String sql = "update user set user = ?,upassword = ? where id = ?";
		try (Connection c = Jdbc.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, user.getUser());
			ps.setString(2, user.getUpassword());
			ps.setInt(3, user.getId());
			ps.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	public void delete(int id){
		String sql = "delete from user where id = ?";
		try (Connection c = Jdbc.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	public List<User> listUser(){
		String sql = "select * from user";
		List<User> users = new ArrayList<>();
		try (Connection c = Jdbc.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				User user = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
				users.add(user);
			}
			return users;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}
	public List<User> search(String name) {
		List<User> users = new ArrayList<>();

		String sql = "select * from user where user like '%" + name + "%'";
		try (Connection c = Jdbc.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User(rs.getInt(1),rs.getString(2),rs.getString(3));
				users.add(user);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return users;
	}
	public static void main(String[] args) {
		System.out.println(new UserDao().getOne("root","admin"));
		System.out.println(new UserDao().getOne("root","admin2"));
		String sql = "update user set total +=1 where id = ...";
	}
}
