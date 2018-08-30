package com.garylee.util;

import com.garylee.test.Test;
import com.sun.mail.smtp.SMTPTransport;

import javax.mail.*;
import java.util.Properties;
//判断用户的账号和口令是否一致，是则存入数据库，不是则返回false
public class RegisterUtil{
    String name = "";
    String password = "";
    public RegisterUtil(String name,String password) throws MessagingException {
        this.name = name;
        this.password = password;
    }
    public boolean isTrue(){
        Properties properties = new Properties();
        properties.put("mail.pop3.ssl.enable",true);
        properties.put("mail.pop3.host","pop.qq.com");
        properties.put("mail.pop3.port",995);
        Session session = Session.getDefaultInstance(properties);
        try {
            Store store = session.getStore("pop3");
            store.connect(name, password);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(new RegisterUtil("664306561@qq.com","hcytmmtlmzvjbeeb").isTrue());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
