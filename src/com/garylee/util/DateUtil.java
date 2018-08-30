package com.garylee.util;

import com.garylee.dao.EmailDao;
import com.garylee.view.login;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public class DateUtil {

    public static String format(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sdf.format(date);
        return s;
    }
    public static Date formatDate(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse(time);
        return d;
    }
    public static Date getTime(int hour,int minute){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();
        return time;
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(new DateUtil().format(new Date()));
        System.out.println(new DateUtil().format(new Date(Long.parseLong("Wed Jun 06 22:50:40 CST 2018"))));

    }
}
