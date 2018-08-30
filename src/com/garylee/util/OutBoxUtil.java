package com.garylee.util;

import com.garylee.domain.Email;
import com.garylee.domain.User;
import com.garylee.view.login;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class OutBoxUtil{
    private Store store = null;
    private Folder folder = null;
    public OutBoxUtil(User user){
        Properties properties = new Properties();
        properties.put("mail.pop3.ssl.enable",true);
        properties.put("mail.pop3.host","pop.qq.com");
        properties.put("mail.pop3.port",995);
        Session session = Session.getDefaultInstance(properties);
        try {
            store = session.getStore("pop3");
//            store.connect("664306561@qq.com", "hcytmmtlmzvjbeeb");
            store.connect(user.getUser(), user.getUpassword());
            folder = store.getFolder("inbox");
            folder.open(Folder.READ_ONLY);//在这一步，收件箱所有邮件将被下载到本地
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取list
    public List<Email> getEmails(){
        List<Email> emails = new ArrayList<>();
        try {
            for (int i = 1; i <= getTotal(); i++) {
                Email email = new Email();
                email.setTitle(getTitle(i));
                email.setContent(getContent(i).toString());
                email.setFrom(getFrom(i));
                email.setTime(getTime(i));
//                email.setTo(getTo(i));
                emails.add(email);
            }
        }catch (Exception e){

        }
        return emails;
    }
    //获取邮件数量
    public int getTotal() throws MessagingException {
       return folder.getMessageCount();
    }
    //获取第n封邮件标题
    public String getTitle(int num) throws MessagingException {
        return folder.getMessage(num).getSubject();
    }
//    //获取内容
//    public Object getContent(int num) throws MessagingException, IOException {
//        return folder.getMessage(num).getContent();
////        Multipart multipart = (Multipart) folder.getMessage(num).getContent();
////        multipart.getBodyPart(1);
////        return multipart.getBodyPart(1);
//    }
    //获取时间
    public String getTime(int num) throws MessagingException, ParseException {
        Date date = folder.getMessage(num).getSentDate();
        return new DateUtil().format(date);
    }

    public String getFrom(int num) throws MessagingException {
        Address address = folder.getMessage(num).getFrom()[0];
        String s = String.valueOf(address);
        if(s.contains("<")&&s.contains(">")){
            int start = s.indexOf("<")+1;
            int end = s.indexOf(">");
            s = s.substring(start,end);
        }

        return s;
    }
    //获取正文(一般getContent只获取正文+附件的multipart)
    public StringBuffer getContents(int num) throws MessagingException, IOException {
        MimeMessage mimeMessage = (MimeMessage) folder.getMessage(new OutBoxUtil(login.user).getTotal()-16);
        Multipart multipart = (Multipart) mimeMessage.getContent();
        int partCount = multipart.getCount();
        //返回正文部分(1开始则为附件部分)
        BodyPart bodyPart = multipart.getBodyPart(0);
        StringBuffer content = new StringBuffer();
        content.append(bodyPart.getContent().toString());
        return content;
    }
    public static void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {
        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
        if (part.isMimeType("text/*") && !isContainTextAttach) {
            content.append(part.getContent().toString());
        } else if (part.isMimeType("message/rfc822")) {
            getMailTextContent((Part)part.getContent(),content);
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                getMailTextContent(bodyPart,content);
            }
        }
    }

    public String getContent(int num) throws Exception {
        StringBuffer content = new StringBuffer(3000);
        MimeMessage msg = (MimeMessage) folder.getMessage(num);
//        StringBuffer stringBuffer = getMailTextContent(new OutBoxUtil(login.user).getTotal());
        getMailTextContent(msg,content);
        return new String(content);
    }
    public static void main(String[] args) throws Exception {
        OutBoxUtil outBoxUtil = new OutBoxUtil(login.user);
        for(int i=1;i<=outBoxUtil.getTotal();i++){
            System.err.println(i);
            System.out.println(outBoxUtil.getTitle(i));
        }
    }

}
