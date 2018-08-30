package com.garylee.view2;

import com.garylee.dao.UserDao;
import com.garylee.domain.User;
import com.garylee.util.RegisterUtil;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.MessagingException;
import javax.swing.*;

public class register extends JFrame{
	private JLabel title1 = new JLabel("1024");
	private JLabel title2 = new JLabel("邮箱管理系统");
	private JLabel number = new JLabel("帐号:");
	private JLabel password = new JLabel("密码:");
	private JTextField numbertxt = new JTextField(10);
	private JPasswordField passwordtxt = new JPasswordField(10);
	private JButton register = new JButton();
	private JPanel bg= new JPanel();
	private JPanel panel = new JPanel();
	
	public register(){
		ImageIcon img = new ImageIcon("src/bg/register.png");
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(panel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		bg.setBounds(0, 0, 580, 500);
		bg.setBackground(Color.white);
		
		panel.setBounds(0, -5, 580, 500);
		panel.setOpaque(false);
		panel.add(imgLabel);
		
		title1.setFont(new   java.awt.Font("Dialog",3,50));
		title1.setForeground(new Color(129,154,254));
		title1.setBounds(150, 40, 125, 50);
		
		title2.setFont(new   java.awt.Font("Dialog",1,25));
		title2.setForeground(new Color(129,154,254));
		title2.setBounds(265, 50, 200, 50);
		
		number.setBounds(105, 170, 70, 30);
		number.setFont(new   java.awt.Font("微软雅黑",1,20));
		
		password.setBounds(105, 270, 70, 30);
		password.setFont(new   java.awt.Font("微软雅黑",1,20));
		
		numbertxt.setBounds(155, 167, 300, 40);
		passwordtxt.setBounds(155, 267, 300, 40);
		
		register.setBounds(115, 389, 355, 58);
		register.setBorderPainted(false);
		register.setBackground(Color.white);
		register.setOpaque(false);
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = numbertxt.getText();
				String password = new String(passwordtxt.getPassword());
				System.out.println("name:"+name);
				System.out.println("password:"+password);
				try {
					if(new UserDao().getOne(name,password)!=null){
						JOptionPane.showMessageDialog(null,"用户已存在!无需重复注册!");
					} else if(new RegisterUtil(name,password).isTrue()){
						User user = new User(name,password);
						new UserDao().add(user);
						JOptionPane.showMessageDialog(null,"注册成功");
					}else {
						JOptionPane.showMessageDialog(null,"用户名或口令错误!");
					}
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}
			}
		});

		this.setLayout(null);
		this.setSize(580, 530);
		this.setLocation(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		this.add(title1);
		this.add(title2);
		this.add(number);
		this.add(password);
		this.add(numbertxt);
		this.add(passwordtxt);
		this.add(register);
		this.add(panel);
		this.add(bg);
	}
	public static void main(String[] args) {
		new register();
	}
}
