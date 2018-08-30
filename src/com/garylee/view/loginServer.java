package com.garylee.view;

import com.garylee.dao.UserDao;
import com.garylee.tcp.MyClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginServer extends JFrame{
	public static String cookie = null;
	private JLabel title1 = new JLabel("1024");
	private JLabel title2 = new JLabel("邮箱管理系统");
	private JPanel bg= new JPanel();
	private JPanel panel = new JPanel();
	private JButton loginbtn = new JButton();
	private JButton register = new JButton();
	private JRadioButton userbtn = new JRadioButton("用户");
	private JRadioButton managerbtn = new JRadioButton("管理员");
	private ButtonGroup btng = new ButtonGroup();
	private JTextField textField = new JTextField(10);
	private JPasswordField passwordField = new JPasswordField(10);

	public loginServer(){
		//设置背景
		ImageIcon img = new ImageIcon("login.png");
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(panel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		bg.setBounds(0, 0, 950, 490);
		bg.setBackground(Color.white);
		//
		panel.setBounds(0, 0, 950, 490);
		panel.setOpaque(false);
		panel.add(imgLabel);
		
		//
		title1.setFont(new   java.awt.Font("Dialog",3,50));
		title1.setForeground(new Color(129,154,254));
		title1.setBounds(5, 50, 125, 50);
		title2.setFont(new   java.awt.Font("Dialog",1,25));
		title2.setForeground(new Color(129,154,254));
		title2.setBounds(120, 60, 200, 50);
		//
		loginbtn.setBounds(627,275, 285, 40);
		loginbtn.setBorderPainted(false);
		loginbtn.setBackground(Color.white);
		loginbtn.setOpaque(false);
		loginbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				String password = new String(passwordField.getPassword());
				if(name.isEmpty())
					JOptionPane.showMessageDialog(null, "用户名不能为空!");
				else if(password.isEmpty())
					JOptionPane.showMessageDialog(null, "密码不能为空!");
				else if (userbtn.isSelected()) {
					//服务器登陆验证测试
					if (new MyClient().login(name,password)==null) {
						JOptionPane.showMessageDialog(null, "用户名不存在或密码错误!");
					} else {
						setVisible(false);
						cookie = name;
						new homepage();
					}
				} else if (managerbtn.isSelected()) {
					if (!new UserDao().getAdmin(name,password)) {
						JOptionPane.showMessageDialog(null, "用户名不存在或密码错误!");
					} else {
						setVisible(false);
						//设置用户登陆信息
						cookie = name;
						new manager();
					}
				}
			}
		});

		register.setBounds(775,395, 75, 15);
		register.setBorderPainted(false);
		register.setBackground(Color.white);
		register.setOpaque(false);
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new register();
			}
		});
		
		textField.setBounds(626,125,285,45);
		passwordField.setBounds(626, 175, 285, 45);
		
		userbtn.setBounds(680, 220, 90, 55);
		userbtn.setOpaque(false);
		managerbtn.setBounds(780, 220, 90, 55);
		managerbtn.setOpaque(false);
		
		btng.add(userbtn);
		btng.add(managerbtn);
		
		
		this.setLayout(null);
		this.setSize(950, 490);
		this.setLocation(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
	    this.add(userbtn);
		this.add(managerbtn);
		this.add(title1);
		this.add(title2);
		this.add(loginbtn);
		this.add(register);
		this.add(textField);
		this.add(passwordField);
		this.add(panel);
		this.add(bg);
		
	}
	public static void main(String[] args) {
		new loginServer();
	}
}
