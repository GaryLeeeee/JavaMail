package com.garylee.view2;

import com.garylee.domain.Email;
import com.garylee.util.DateUtil;
import com.garylee.util.InBoxUtil;
import com.garylee.view.login;
import com.garylee.view.timsend;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class timesend extends JFrame{
	private JButton send = new JButton();
	private JButton cancel = new JButton();
	private JTextField year = new JTextField();
	private JTextField month = new JTextField();
	private JTextField day = new JTextField();
	private JTextField hour = new JTextField();
	private JTextField minute = new JTextField();
	private JPanel bg= new JPanel();
	private JPanel panel = new JPanel();
	private Email email;
	public timesend(Email email){
		this();
		this.email = email;
	}
	public timesend(){
		ImageIcon img = new ImageIcon("src/bg/time.png");
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(panel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		bg.setBounds(0, 0, 700, 300);
		bg.setBackground(Color.white);
		//
		panel.setBounds(0, 0, 700, 300);
		panel.add(imgLabel);
		
		send.setBounds(533, 251 ,75, 40);
		send.setBorderPainted(false);
		send.setBackground(Color.white);
		send.setOpaque(false);
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//2018-05-27 09:50:19
				int hours = Integer.parseInt(hour.getText());
				int minutes = Integer.parseInt(minute.getText());
				Date timing = DateUtil.getTime(hours,minutes);
				System.out.println("指定时间time=" + timing);
				Timer timer = new Timer();
				try {
					timer.schedule(new TimeSending(), timing);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		cancel.setBounds(612, 251 ,75, 40);
		cancel.setBorderPainted(false);
		cancel.setBackground(Color.white);
		cancel.setOpaque(false);
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		year.setBounds(37, 122, 115, 36);
		month.setBounds(178, 122, 93, 36);
		day.setBounds(297, 122, 94, 36);
		hour.setBounds(417, 122, 94, 36);
		minute.setBounds(536, 122, 94, 36);
		
		this.setLayout(null);
		this.setSize(700, 335);
		this.setLocation(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		this.add(send);
		this.add(cancel);
		this.add(year);
		this.add(month);
		this.add(day);
		this.add(hour);
		this.add(minute);
		this.add(panel);
		this.add(bg);
	}
	class TimeSending extends TimerTask {

		@Override
		public void run() {
			try {
				new InBoxUtil(login.user).send(email);
				System.out.println("定时发送结束已完成！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new timesend();
	}
}
