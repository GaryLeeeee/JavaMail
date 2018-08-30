package com.garylee.view;

import com.garylee.util.OutBoxUtil;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class homepage extends JFrame{
	private JLabel title = new JLabel("1024");
	private JLabel title2 = new JLabel("邮箱管理系统");
	private JPanel mainpanel = new JPanel();
	private JPanel sidepanel = new JPanel(); 
	private JPanel bg = new JPanel();
   private JButton write = new JButton();
   private JButton received = new JButton();
   private JButton connector = new JButton();
   private JButton draft = new JButton();
   private JButton sent = new JButton();
   private JButton deleted = new JButton();
	private JButton change = new JButton("★一键换肤");
public homepage(){
	//设置图片
//	ImageIcon img = new ImageIcon("side.png");
	ImageIcon img = new ImageIcon("function1.png");
	JLabel imgLabel = new JLabel(img);
	ImageIcon img1 = new ImageIcon("function2.png");
	JLabel imgLabel3 = new JLabel(img1);
	this.getLayeredPane().add(sidepanel, new Integer(Integer.MIN_VALUE));
	 imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
	 ImageIcon img2 = new ImageIcon("main.png");
		JLabel imgLabel2 = new JLabel(img2);
		this.getLayeredPane().add(mainpanel, new Integer(Integer.MIN_VALUE));
		 imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		//设置侧面板 
		 bg.setBounds(0, 0,900, 600);
		 bg.setBackground(Color.white);
		 
	 sidepanel.setBounds(5, 10, 250, 500);
	 sidepanel.setOpaque(false);
	 sidepanel.add(imgLabel);
	 sidepanel.add(imgLabel3);
	 //设置主面板
	 mainpanel.setBounds(255, 35, 635, 370);
	 mainpanel.setOpaque(false);
	 mainpanel.add(imgLabel2);
	 //设置按钮
	 title.setFont(new   java.awt.Font("Dialog",   3,   50));   
	 title.setForeground(new Color(129,154,254));
	 title.setBounds(260, 5, 125, 50);
	 title2.setBounds(385, 10, 200, 50);
	 title2.setFont(new   java.awt.Font("Dialog",   1,   25));   
	 title2.setForeground(new Color(129,154,254));
	 //
	change.setBounds(700, 10, 100, 30);
	change.setBackground(Color.white);
	change.setBorderPainted(false);
	change.setFocusPainted(false);
	change.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			new com.garylee.view2.homepage();
		}
	});
	write.setBounds(12,25,235, 40);
	 write.setBorderPainted(false);
	 write.setBackground(Color.white);
//	 write .setFont(new java.awt.Font("Dialog", 1, 20));
	 write.setOpaque(false);
	 write.addActionListener(new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
//			 setVisible(false);
			 new writemail();
		 }
	 });
	 
	 received.setBounds(12,75,235, 40);
	 received.setBorderPainted(false);
	 received.setBackground(Color.white);
//	 received .setFont(new java.awt.Font("Dialog", 1, 20));
	 received.setOpaque(false);
	 received.addActionListener(new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
//			 setVisible(false);
			 new inbox();
		 }
	 });
	 connector.setBounds(12,125,235, 40);
	 connector.setBorderPainted(false);
	 connector.setBackground(Color.white);
//	 connector .setFont(new java.awt.Font("Dialog", 1, 20));
	 connector.setOpaque(false);
	 connector.addActionListener(new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 new contacts();
		 }
	 });
	 draft.setBounds(12, 195, 235, 40);
	 draft.setBorderPainted(false);
	 draft.setBackground(Color.white);
//	 received2.setFont(new java.awt.Font("Dialog", 1, 20));
	 draft.setOpaque(false);
	 draft.addActionListener(new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 new drafts();
		 }
	 });
	 
	 sent.setBounds(12,235,235, 40);
	 sent.setBorderPainted(false);
	 sent.setBackground(Color.white);
	 sent.setOpaque(false);
	 sent.addActionListener(new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 new outbox();
		 }
	 });
	 
	 deleted.setBounds(12,276,235, 40);
	 deleted.setBorderPainted(false);
	 deleted.setBackground(Color.white);
	 deleted.setOpaque(false);
	 //write.setContentAreaFilled(false);
	deleted.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			new deleted();
		}
	});
	this.setLayout(null);
	this.setSize(900, 600);
	this.setLocation(400, 300);
	 this.setLocationRelativeTo(null);
	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 this.setLocationRelativeTo(null);
	 this.setResizable(false);
	 this.setVisible(true);
	this.add(change);
	this.add(title);
	this.add(write);
	this.add(title2);
	this.add(connector);
    this.add(received);
    this.add(draft);
    this.add(sent);
    this.add(deleted);
	 this.add(sidepanel);
	 this.add(mainpanel);
	 this.add(bg);
	 
}
public static void main(String[] args){
	
	 new homepage();
}
}