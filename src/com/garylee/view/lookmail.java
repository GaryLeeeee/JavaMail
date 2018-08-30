package com.garylee.view;

import com.garylee.dao.SmailDao;
import com.garylee.domain.AttachTable;
import com.garylee.domain.Email;
import com.garylee.util.FileUtil;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class lookmail extends JFrame{
	private JButton returnbtn = new JButton();
	private JButton replybtn = new JButton();
	private JButton lookbtn = new JButton();
	private JTextArea mainbodytxt = new JTextArea();
	AttachTable attachTable = new AttachTable();
	private JTable table = new JTable(attachTable);
	private JPanel bg= new JPanel();
	private JPanel panel = new JPanel();
	private JPanel tablePane = new JPanel();
	private JPanel areaPane = new JPanel();
	private JLabel themelb = new JLabel("//");
	private JLabel addresserlb = new JLabel("//");
	private JLabel timelb = new JLabel("//");
	private JLabel receiverlb = new JLabel("//");
	private Email email;
	public lookmail(Email email){
		this();
		this.email = email;
		themelb.setText(email.getTitle());
		addresserlb.setText(email.getFrom());
		timelb.setText(email.getTime());
		receiverlb.setText(email.getTo());
		mainbodytxt.setText(email.getContent());
	}
	public lookmail(){
		ImageIcon img = new ImageIcon("lookmail.png");
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(panel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		bg.setBounds(0, 0, 1100, 700);
		bg.setBackground(Color.white);

		panel.setBounds(0, -5, 1100, 700);
		panel.add(imgLabel);
		panel.setOpaque(false);

		returnbtn.setBounds(5, 5, 66, 32);
		returnbtn.setBorderPainted(false);
		returnbtn.setBackground(Color.white);
		returnbtn.setOpaque(false);
		returnbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		replybtn.setBounds(88, 5, 66, 32);
		replybtn.setBorderPainted(false);
		replybtn.setBackground(Color.white);
		replybtn.setOpaque(false);
		replybtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				email.setTitle("回复: "+email.getTitle());
				email.setContent("");
				//以下两个不可换顺序
				email.setTo(email.getFrom());
				email.setFrom(login.user.getUser());//设置发件人，也就是当前登陆用户
				new writemail(email);
			}
		});

		themelb.setBounds(120, 50, 200, 30);
		addresserlb.setBounds(120, 88, 200, 30);
		timelb.setBounds(120, 120, 200, 30);
		receiverlb.setBounds(120, 152, 200, 30);

		lookbtn.setBounds(200, 600, 89, 30);
		lookbtn.setIcon(new ImageIcon("look.png"));
		lookbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					//判断选中哪一行
					int num = table.getSelectedRow();
					//选中附件的file
					File attach = new File(attachTable.strings.get(num).getPath());
					//打开附件
					FileUtil.openFile(attach);
				}
			}
		});

		mainbodytxt.setBounds(5, 185, 1035, 295);
		mainbodytxt.setLineWrap(true);
		mainbodytxt.setEditable(false);
//		mainbodytxt.setBorder(new LineBorder(new java.awt.Color(85,86,86), 1, false));

		areaPane.setLayout(null);

		areaPane.setLayout(null);
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setViewportView(mainbodytxt);
		scrollPane2.setBounds(0, 0, 1035, 295);
		areaPane.add(scrollPane2);
		areaPane.setBounds(5, 185, 1035, 295);

		table.setBounds(10,545, 180, 135);
		table.setRowHeight(25);

		tablePane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(0, 0, 180, 135);
		tablePane.add(scrollPane);
		tablePane.setBounds(10,545, 180, 135);

		this.setLayout(null);
		this.setSize(1050, 720);
		this.setLocation(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);

		this.add(themelb);
		this.add(addresserlb);
		this.add(timelb);
		this.add(receiverlb);
		this.add(returnbtn);
		this.add(replybtn);
		this.add(lookbtn);
		this.add(areaPane);
		this.add(tablePane);
		this.add(panel);
		this.add(bg);
	}
	public static void main(String[] args) {
		new lookmail();
	}
}
