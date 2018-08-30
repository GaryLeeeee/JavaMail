package com.garylee.util;

import com.garylee.domain.Email;
import com.garylee.domain.User;
import com.garylee.view.login;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class AttachUtil{
    private Store store = null;
    private Folder folder = null;
    public AttachUtil(User user){
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

            Message[] messages = folder.getMessages();


//            folder.close(true);
//            store.close();
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
    //获取内容
    public Object getContent(int num) throws MessagingException, IOException {
        return folder.getMessage(num).getContent();
//        Multipart multipart = (Multipart) folder.getMessage(num).getContent();
//        multipart.getBodyPart(1);
//        return multipart.getBodyPart(1);
    }
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
    public static void saveAttachment(Part part, String destDir) throws UnsupportedEncodingException, MessagingException,
            FileNotFoundException, IOException {
        if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();    //复杂体邮件
            //复杂体邮件包含多个邮件体
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                //获得复杂体邮件中其中一个邮件体
                BodyPart bodyPart = multipart.getBodyPart(i);
                //某一个邮件体也有可能是由多个邮件体组成的复杂体
                String disp = bodyPart.getDisposition();
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
                    InputStream is = bodyPart.getInputStream();
                    saveFile(is, destDir, decodeText(bodyPart.getFileName()));
                } else if (bodyPart.isMimeType("multipart/*")) {
                    saveAttachment(bodyPart,destDir);
                } else {
                    String contentType = bodyPart.getContentType();
                    if (contentType.indexOf("name") != -1 || contentType.indexOf("application") != -1) {
                        saveFile(bodyPart.getInputStream(), destDir, decodeText(bodyPart.getFileName()));
                    }
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            saveAttachment((Part) part.getContent(),destDir);
        }
    }
    /**
     * 读取输入流中的数据保存至指定目录
     * @param is 输入流
     * @param fileName 文件名
     * @param destDir 文件存储目录
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void saveFile(InputStream is, String destDir, String fileName)
            throws FileNotFoundException, IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(new File(destDir + fileName)));
        int len = -1;
        while ((len = bis.read()) != -1) {
            bos.write(len);
            bos.flush();
        }
        bos.close();
        bis.close();
    }

    /**
     * 文本解码
     * @param encodeText 解码MimeUtility.encodeText(String text)方法编码后的文本
     * @return 解码后的文本
     * @throws UnsupportedEncodingException
     */
    public static String decodeText(String encodeText) throws UnsupportedEncodingException {
        if (encodeText == null || "".equals(encodeText)) {
            return "";
        } else {
            return MimeUtility.decodeText(encodeText);
        }
    }
    //注意删除邮箱中的"订阅",否则也会获取,但收件箱中不获取
    public void save(int num){
        try {
            MimeMessage mimeMessage = (MimeMessage) folder.getMessage(num);
            //判断是否有附件
            if(isContainAttachment(mimeMessage)) {
                //用用户账号作为父文件夹，收件日期作为子文件进行存储附件操作
                String date = DateUtil.format(mimeMessage.getSentDate());
                date = date.replaceAll(":", "-");
                File file = new File("d:\\Users\\Administrator\\Desktop\\attach\\" + login.user.getUser() + "\\" + date);
                if (!file.exists())
                    file.mkdirs();
                saveAttachment(mimeMessage, file.getAbsolutePath() + "\\");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //判断是否有附件
    public static boolean isContainAttachment(Part part) throws MessagingException, IOException {
        boolean flag = false;
        if (part.isMimeType("multipart/*")) {
            MimeMultipart multipart = (MimeMultipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                String disp = bodyPart.getDisposition();
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
                    flag = true;
                } else if (bodyPart.isMimeType("multipart/*")) {
                    flag = isContainAttachment(bodyPart);
                } else {
                    String contentType = bodyPart.getContentType();
                    if (contentType.indexOf("application") != -1) {
                        flag = true;
                    }

                    if (contentType.indexOf("name") != -1) {
                        flag = true;
                    }
                }

                if (flag) break;
            }
        } else if (part.isMimeType("message/rfc822")) {
            flag = isContainAttachment((Part)part.getContent());
        }
        return flag;
    }
    public static void main(String[] args) throws MessagingException, ParseException {
        int total = new AttachUtil(login.user).getTotal();
        System.out.println("total:"+total);
        new AttachUtil(login.user).save(63);
    }
}