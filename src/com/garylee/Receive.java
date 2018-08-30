package com.garylee;


import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Date;
import java.util.Properties;

public class Receive {
    public static void main(String[] args){
        String protocol = "pop3";//使用pop3协议
        boolean isSSL = true;//使用SSL加密
        String host = "pop.qq.com";//QQ邮箱的pop3服务器
        int port = 995;//端口
        String username = "664306561@qq.com";//用户名
        String password = "hcytmmtlmzvjbeeb";//密码

       /*
       *Properties是一个属性对象，用来创建Session对象
       */
        Properties props = new Properties();
        props.put("mail.pop3.ssl.enable", isSSL);
        props.put("mail.pop3.host", host);
        props.put("mail.pop3.port", port);
       /*
       *Session类定义了一个基本的邮件对话。
       */
        Session session = Session.getDefaultInstance(props);

       /*
        * Store类实现特定邮件协议上的读、写、监视、查找等操作。
        * 通过Store类可以访问Folder类。
        * Folder类用于分级组织邮件，并提供照Message格式访问email的能力。
        */
        Store store = null;
        Folder folder = null;
        try {
            store = session.getStore(protocol);
            store.connect(username, password);

            folder = store.getFolder("inbox");
            folder.open(Folder.READ_ONLY);//在这一步，收件箱所有邮件将被下载到本地

            int size = folder.getMessageCount();//获取邮件数目
            System.out.println("Size:"+size);
//            Message message = folder.getMessage(size);//取得最新的那个邮件
            for(int i=1;i<=size;i++) {
                Message message = folder.getMessage(i);

                //解析邮件内容
//                String from = message.getFrom()[0].toString();
                String subject = message.getSubject();
//                Date date = message.getSentDate();

//                System.out.println("Content:" + message.getContent());
//                System.out.println("From: " + from);
                System.out.println("Subject: " + subject);
                System.out.println("if:"+message);
//                System.out.println("Date: " + date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (folder != null) {
                    folder.close(false);
                }
                if (store != null) {
                    store.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("接收完毕！");
    }
}
