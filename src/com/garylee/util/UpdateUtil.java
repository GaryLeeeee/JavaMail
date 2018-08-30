package com.garylee.util;

import com.garylee.dao.DraftsDao;
import com.garylee.dao.Jdbc;
import com.garylee.domain.Email;
import com.garylee.domain.User;
import com.garylee.view.login;

import javax.mail.MessagingException;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

//将邮箱中的收件箱存入数据库
public class UpdateUtil {
    //删除数据库中该用户
    public void delInbox(User user){
        String sql = "delete from email where email.to = ?";
        try (Connection c = Jdbc.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, user.getUser());
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    //获取用户邮箱收件箱到数据库
    public void getInbox(User user){
        List<Email> emails = new OutBoxUtil(user).getEmails();
        String sql = "insert into email values(null,?,?,?,?,?)";
        try (Connection c = Jdbc.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            int i=1;
            int total = new OutBoxUtil(user).getTotal();
            for(Email email:emails){
                System.out.println("-正在解析第"+(i++)+"封邮件,总共有"+total+"封邮件-");
                ps.setString(1, email.getTitle());
                ps.setString(2, email.getContent());
                ps.setString(3, email.getTime());
//            ps.setDate(3, new java.sql.Date(new Date().getTime()));
                ps.setString(4, email.getFrom());
                ps.setString(5, login.user.getUser());
                ps.execute();
            }
            System.out.println("解析完毕!");

        } catch (SQLException e) {

            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void delAttach(User user){
        FileUtil.delFolder("d:\\Users\\Administrator\\Desktop\\attach\\"+user.getUser());
    }
    public void getAttach(User user){
        AttachUtil attachUtil = new AttachUtil(user);
        try {
            for (int i = 1; i <= attachUtil.getTotal(); i++) {
                attachUtil.save(i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //更新操作，即先删除本地，再下载邮件数据
    public void updateInbox(User user){
        delInbox(user);
        getInbox(user);
        delAttach(user);
        getAttach(user);
    }

    public static void main(String[] args) {
//        new UpdateUtil().updateInbox(login.user);
//        new UpdateUtil().delInbox(login.user);
        new UpdateUtil().updateInbox(login.user);
    }
}
