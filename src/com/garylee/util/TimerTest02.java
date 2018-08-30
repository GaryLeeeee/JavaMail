package com.garylee.util;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.*;

import static com.garylee.util.InBoxUtil.writeFiles;

public class TimerTest02 {
    Timer timer;
	private MimeMessage message;

	public TimerTest02() {
		Date time = getTime();
		System.out.println("指定时间time=" + time);
		timer = new Timer();
		timer.schedule(new TimerTaskTest02(), time);
	}

	public Date getTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 15);
		calendar.set(Calendar.MINUTE, 22);
		calendar.set(Calendar.SECOND, 20);
		Date time = calendar.getTime();
		return time;
	}

	public static void main(String[] args) {
		new TimerTest02();

	}
}
class TimerTaskTest02 extends TimerTask {
	@Override
	public void run() {
		System.out.println("任务完成");
		System.out.println(new Date());
	}
}