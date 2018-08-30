package com.garylee.view2;

import com.garylee.dao.UserDao;
import com.garylee.domain.User;
import com.garylee.view.homepage;
import com.garylee.view.manager;
import com.garylee.view.register;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class login extends JFrame{
	public static String cookie = null;
	public static User user = new User("664306561@qq.com", "hcytmmtlmzvjbeeb");
	private JLabel title1 = new JLabel("1024");
	private JLabel title2 = new JLabel("邮箱管理系统");
	private JLabel title3 = new JLabel("帐号密码登录");
	private JButton loginbtn = new JButton();
	private JButton register = new JButton();
	private JRadioButton userbtn = new JRadioButton("用户");
	private JRadioButton managerbtn = new JRadioButton("管理员");
	private ButtonGroup btng = new ButtonGroup();
	private JTextField number = new JTextField(10);
	private JPasswordField password = new JPasswordField(10);
	private JPanel bg= new JPanel();
	private JPanel panel = new JPanel();
	
	public login(){
		ImageIcon img = new ImageIcon("src/bg/login.png");
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(panel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		bg.setBounds(0, 0, 1244, 775);
		bg.setBackground(Color.white);
		
		panel.setBounds(0, -5, 1244, 775);
		panel.setOpaque(false);
		panel.add(imgLabel);
		
		title1.setFont(new   java.awt.Font("Dialog",3,50));
		title1.setForeground(new Color(129,154,254));
//		title1.setBounds(550, 180, 125, 50);
		title1.setBounds(580, 140, 125, 50);
		title2.setFont(new   java.awt.Font("Dialog",1,25));
		title2.setForeground(new Color(129,154,254));
//		title2.setBounds(670, 190, 200, 50);
		title2.setBounds(700, 150, 200, 50);
		
		title3.setFont(new   java.awt.Font("微软雅黑",1,30));
		title3.setBounds(615, 220, 200, 50);
		
		number.setBounds(521,304,375,50);
		password.setBounds(521,380,375,50);
		
		loginbtn.setBounds(522,491, 173, 60);
		loginbtn.setBorderPainted(false);
		loginbtn.setBackground(Color.white);
		loginbtn.setOpaque(false);
		loginbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = number.getText();
				String pwd = new String(password.getPassword());
				if(name.isEmpty())
					JOptionPane.showMessageDialog(null, "用户名不能为空!");
				else if(pwd.isEmpty())
					JOptionPane.showMessageDialog(null, "密码不能为空!");
				else if (userbtn.isSelected()) {
					if (new UserDao().getOne(name,pwd)==null) {
						JOptionPane.showMessageDialog(null, "用户名不存在或密码错误!");
					} else {
						setVisible(false);
						cookie = name;
						new com.garylee.view2.homepage();
					}
				} else if (managerbtn.isSelected()) {
					if (!new UserDao().getAdmin(name,pwd)) {
						JOptionPane.showMessageDialog(null, "用户名不存在或密码错误!");
					} else {
						setVisible(false);
						//设置用户登陆信息
						cookie = name;
						new com.garylee.view2.manager();
					}
				}
			}
		});
		register.setBounds(723,491, 174, 60);
		register.setBorderPainted(false);
		register.setBackground(Color.white);
		register.setOpaque(false);
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new com.garylee.view2.register();
			}
		});
		userbtn.setBounds(625, 425, 100, 70);
		userbtn.setOpaque(false);
		managerbtn.setBounds(740, 425, 100, 70);
		managerbtn.setOpaque(false);
		
		btng.add(userbtn);
		btng.add(managerbtn);
		
		
		this.setLayout(null);
		this.setSize(1244, 775);
		this.setLocation(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		this.add(title1);
		this.add(title2);
		this.add(title3);
		this.add(number);
		this.add(password);
		this.add(loginbtn);
		this.add(register);
		this.add(userbtn);
		this.add(managerbtn);
		this.add(panel);
		this.add(bg);
		
	}
	public static void main(String[] args) {
		new login();
	}
}
