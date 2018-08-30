package com.garylee.util;

import com.garylee.domain.Email;
import com.garylee.domain.User;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.TimerTask;

public class InBoxUtil {
    // 创建Properties 类用于记录邮箱的一些属性
    Properties props = new Properties();
    Authenticator authenticator;
    Session mailSession;
    public static BodyPart writeFiles(String filePath)throws IOException, MessagingException{
        File file = new File(filePath);
        if(!file.exists()){
            throw new IOException("文件不存在!请确定文件路径是否正确");
        }
        MimeBodyPart bodypart = new MimeBodyPart();
        DataSource dataSource = new FileDataSource(file);
        bodypart.setDataHandler(new DataHandler(dataSource));
        //文件名要加入编码，不然出现乱码
        bodypart.setFileName(MimeUtility.encodeText(file.getName()));
        return bodypart;
    }
    public InBoxUtil(User user){
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", "smtp.qq.com");
        //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
        props.put("mail.smtp.port", "587");
        // 此处填写你的账号
        props.put("mail.user", user.getUser());
        // 此处的密码就是前面说的16位STMP口令
        props.put("mail.password", user.getUpassword());
        // 构建授权信息，用于进行SMTP进行身份验证
        authenticator = new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        mailSession = Session.getInstance(props, authenticator);
//    mailSession.setDebug(true);
    }
    public void send(Email email) throws Exception {
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人的邮箱
        InternetAddress to = new InternetAddress(email.getTo());
        message.setRecipient(MimeMessage.RecipientType.TO, to);

        // 设置邮件标题
        message.setSubject(email.getTitle());

        Multipart multipart = new MimeMultipart();
        // 设置邮件的内容体
        MimeBodyPart textpart = new MimeBodyPart();
        textpart.setText(email.getContent());
        multipart.addBodyPart(textpart);
        //设置收件箱显示时间
        message.setSentDate(email.getDate());
        //附件测试
        if(!email.getAttachment().isEmpty()) {
            for (String s : email.getAttachment())
                multipart.addBodyPart(writeFiles(s));
        }
        message.setContent(multipart,"text/plain;charset=UTF-8");
//        message.saveChanges();
            //发送邮件啦
            Transport.send(message);

    }

    public static void main(String[] args) throws Exception {
        User user = new User("664306561@qq.com","hcytmmtlmzvjbeeb");
        Email email = new Email();
        email.setContent("这是正文");
        email.setTitle("这是标题");
        email.setTo("664306561@qq.com");
        new InBoxUtil(user).send(email);
    }
}
