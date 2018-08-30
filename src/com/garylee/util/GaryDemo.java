package com.garylee.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static com.garylee.util.InBoxUtil.writeFiles;

public class GaryDemo {
    public static void main(String[] args) throws Exception {
	 // 创建Properties 类用于记录邮箱的一些属性
    Properties props = new Properties();
    // 表示SMTP发送邮件，必须进行身份验证
    props.put("mail.smtp.auth", "true");
    //此处填写SMTP服务器
    props.put("mail.smtp.host", "smtp.qq.com");
    //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
    props.put("mail.smtp.port", "587");
    // 此处填写你的账号
    props.put("mail.user", "664306561@qq.com");
    // 此处的密码就是前面说的16位STMP口令
    props.put("mail.password", "hcytmmtlmzvjbeeb");

    // 构建授权信息，用于进行SMTP进行身份验证
    Authenticator authenticator = new Authenticator() {

        protected PasswordAuthentication getPasswordAuthentication() {
            // 用户名、密码
            String userName = props.getProperty("mail.user");
            String password = props.getProperty("mail.password");
            return new PasswordAuthentication(userName, password);
        }
    };
    // 使用环境属性和授权信息，创建邮件会话
    Session mailSession = Session.getInstance(props, authenticator);
//    mailSession.setDebug(true);
    // 创建邮件消息
    MimeMessage message = new MimeMessage(mailSession);
    // 设置发件人
    InternetAddress form = new InternetAddress(
            props.getProperty("mail.user"));
    message.setFrom(form);

    // 设置收件人的邮箱
    InternetAddress to = new InternetAddress("664306561@qq.com");
    message.setRecipient(MimeMessage.RecipientType.TO, to);

    // 设置邮件标题
    message.setSubject("测试标题");

    // 设置邮件的内容体


    //附件测试
        Multipart multipart = new MimeMultipart();
        List<String> list = new ArrayList<>();
//        list.add("d:\\Users\\Administrator\\Desktop\\testimg\\测试.txt");
//        list.add("d:\\Users\\Administrator\\Desktop\\testimg\\1.jpg");
//        //单个附件
        multipart.addBodyPart(writeFiles("d:\\Users\\Administrator\\Desktop\\testimg\\测试.txt"));
        //多个附件
//        for(String s:list)
//            multipart.addBodyPart(writeFiles(s));
        message.setContent(multipart);
//        message.setSentDate(new DateUtil().formatDate("2018-5-27 11:11:00"));
//        message.saveChanges();
    // 最后当然就是发送邮件啦
    Transport.send(message);
    //保存成eml格式到本地
//        OutputStream out = new FileOutputStream("MyEmail.eml");
//        OutputStream out = new FileOutputStream("crafts/MyEmail.txt");
//        message.writeTo(out);
//        out.flush();
//        out.close();

	}

}
