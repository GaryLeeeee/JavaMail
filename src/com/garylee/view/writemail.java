package com.garylee.view;

import com.garylee.dao.DraftsDao;
import com.garylee.dao.SmailDao;
import com.garylee.domain.Deleted;
import com.garylee.domain.Draft;
import com.garylee.domain.Email;
import com.garylee.domain.User;
import com.garylee.util.DateUtil;
import com.garylee.util.InBoxUtil;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Flags;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class writemail extends JFrame implements ActionListener{
	private JButton sendbtn1 = new JButton("发送");
	private JButton timesend1 = new JButton("定时发送");
	private JButton draftbtn1 = new JButton("存草稿");
	private JButton closebtn1 = new JButton("关闭");
	private JButton sendbtn2 = new JButton("发送");
	private JButton timesend2 = new JButton("定时发送");
	private JButton draftbtn2 = new JButton("存草稿");
	private JButton closebtn2 = new JButton("关闭");
	private JLabel enclosure = new JLabel();
	private JButton addenclosure = new JButton("添加附件");
	private JLabel receiver = new JLabel("收件人");
	private JLabel theme = new JLabel("主题");
	private JLabel mainbody = new JLabel("正文");
	private JLabel addresser = new JLabel("发件人:");
	private JTextField receivertxt = new JTextField(20);
	private JTextField themetxt = new JTextField(20);
	private JTextArea mainbodytxt = new JTextArea();
	private JTextField addressertxt = new JTextField(20);
	private JPanel bg= new JPanel();
	private JPanel panel = new JPanel();
	private List<String> strings = new ArrayList<>();
	//以下两个是实现把email直接赋在该页面上
	private Draft draft;
	public writemail(Draft draft){
		writemail writemail = new writemail();
		writemail.receivertxt.setText(draft.getFrom());
		writemail.themetxt.setText(draft.getTitle());
		writemail.mainbodytxt.setText(draft.getContent());
		writemail.addressertxt.setText(draft.getFrom());
	}
	public writemail(Email email){
		writemail writemail = new writemail();
		writemail.receivertxt.setText(email.getFrom());
		writemail.themetxt.setText(email.getTitle());
		writemail.mainbodytxt.setText(email.getContent());
		writemail.addressertxt.setText(email.getTo());
	}
	public writemail(Deleted deleted){
		writemail writemail = new writemail();
		writemail.receivertxt.setText(deleted.getTo());
		writemail.themetxt.setText(deleted.getTitle());
		writemail.mainbodytxt.setText(deleted.getContent());
		writemail.addressertxt.setText(deleted.getFrom());
	}
	public writemail(){
		//
		ImageIcon img = new ImageIcon("writemain.png");
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(panel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		bg.setBounds(0, 0, 1200, 700);
		bg.setBackground(Color.white);
		//
		panel.setBounds(0, 0, 1200, 700);
		panel.setOpaque(false);
		panel.add(imgLabel);
		//
		sendbtn1.setBounds(20, 55, 70, 30);
		sendbtn1.setFont(new Font("微软雅黑",1,16));
		timesend1.setBounds(100, 55, 100, 30);
		timesend1.setFont(new Font("微软雅黑",1,16));
		draftbtn1.setBounds(210, 55, 90, 30);
		draftbtn1.setFont(new Font("微软雅黑",1,16));
		closebtn1.setBounds(310, 55, 70, 30);
		closebtn1.setFont(new Font("微软雅黑",1,16));
		
		receiver.setBounds(50, 105, 50, 20);
		receiver.setForeground(Color.BLUE);
		receivertxt.setBounds(100, 100, 700, 30);
		theme.setBounds(60, 150, 50, 20);
		themetxt.setBounds(100, 145, 700, 30);
//		enclosure.setBounds(190, 190, 90, 30);
		enclosure.setBounds(190, 190,1000,30);
		addenclosure.setBounds(100, 190, 90, 30);
		mainbody.setBounds(60, 240, 50, 20);
		mainbodytxt.setBounds(100, 235, 700, 340);
		mainbodytxt.setLineWrap(true);
//		mainbodytxt.setBorder(new LineBorder(new java.awt.Color(19,59,85), 1, false));
		mainbodytxt.setBorder(new LineBorder(new java.awt.Color(85,86,86), 1, false));
		addresser.setBounds(100, 590, 50, 20);
		addressertxt.setBounds(150, 585, 300, 30);
		//设置默认值为当前用户邮箱，并设置无法修改
		addressertxt.setText("664306561@qq.com");
		addressertxt.setEditable(false);
		
		sendbtn2.setBounds(20, 645, 70, 30);
		sendbtn2.setFont(new Font("微软雅黑",1,16));
		timesend2.setBounds(100, 645, 100, 30);
		timesend2.setFont(new Font("微软雅黑",1,16));
		draftbtn2.setBounds(210, 645, 90, 30);
		draftbtn2.setFont(new Font("微软雅黑",1,16));
		closebtn2.setBounds(310, 645, 70, 30);
		closebtn2.setFont(new Font("微软雅黑",1,16));
		closebtn1.addActionListener(this);
		closebtn2.addActionListener(this);
		
		this.setLayout(null);
		this.setSize(1200, 730);
		this.setLocation(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);


		this.add(sendbtn1);
		sendbtn1.addActionListener(this);
		sendbtn2.addActionListener(this);
		this.add(timesend1);
		timesend1.addActionListener(this);
		timesend2.addActionListener(this);
		this.add(draftbtn1);
		draftbtn1.addActionListener(this);
		draftbtn2.addActionListener(this);
		this.add(closebtn1);
		this.add(receiver);
		this.add(receivertxt);
		this.add(theme);
		this.add(themetxt);
		addenclosure.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);
				File file = fc.getSelectedFile();
				if (returnVal == JFileChooser.APPROVE_OPTION) {
//					JOptionPane.showMessageDialog(null, "计划打开文件:" + file.getAbsolutePath());
					strings.add(file.getAbsolutePath());
					enclosure.setText(enclosure.getText()+"  |  "+file.getName());

				}
			}
		});
		this.add(addenclosure);
		this.add(enclosure);
		this.add(mainbody);
		this.add(mainbodytxt);
		this.add(addresser);
		this.add(addressertxt);
		this.add(sendbtn2);
		this.add(timesend2);
		this.add(draftbtn2);
		this.add(closebtn2);
		this.add(panel);
		this.add(bg);
	}

	public static void main(String[] args) {
		new writemail();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String to = receivertxt.getText();
		String title = themetxt.getText();
		String content = mainbodytxt.getText();
		String from = addressertxt.getText();
		if(e.getSource()==closebtn1||e.getSource()==closebtn2){
			setVisible(false);
			new homepage();
		}else if(e.getSource()==sendbtn1||e.getSource()==sendbtn2){
			try {
				String time = new DateUtil().format(new Date());
				System.out.println("to:"+to);
				System.out.println("title:"+title);
				System.out.println("content:"+content);
				System.out.println("from:"+from);
				System.out.println("time:"+time);
				Email email = new Email();
				email.setTo(to);
				email.setTitle(title);
				email.setContent(content);
				email.setFrom(from);
				email.setAttachment(strings);
				email.setTime(time);
				new InBoxUtil(login.user).send(email);
				new SmailDao().addSmail(email);
				System.out.println("发送成功");
				new feedback();
				setVisible(false);
			} catch (ParseException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==timesend1||e.getSource()==timesend2){
			Email email = new Email();
			email.setTo(to);
			email.setTitle(title);
			email.setContent(content);
			email.setFrom(from);
			email.setAttachment(strings);
			new timsend(email);
		}else if(e.getSource()==draftbtn1||e.getSource()==draftbtn2){
			new DraftsDao().addDrafts(new Draft(from,to,title,content));
			JOptionPane.showMessageDialog(null, "存草稿成功");
		}

	}
}
