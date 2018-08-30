package com.garylee.view2;

import com.garylee.dao.DraftsDao;
import com.garylee.dao.SmailDao;
import com.garylee.domain.Deleted;
import com.garylee.domain.Draft;
import com.garylee.domain.Email;
import com.garylee.util.DateUtil;
import com.garylee.util.InBoxUtil;
import com.garylee.view.feedback;
import com.garylee.view.homepage;
import com.garylee.view.timsend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class writemail extends JFrame implements ActionListener{
	private JButton sendbtn = new JButton();
	private JButton draftbtn = new JButton();
	private JButton cancelbtn = new JButton();
	private JButton timesend = new JButton();
	private JButton addenclosure = new JButton();
	private JLabel enclosure = new JLabel();
	private JLabel addresser = new JLabel("发件人");
	private JLabel receiver = new JLabel("收件人");
	private JLabel theme = new JLabel("主题");
	private JLabel mainbody = new JLabel("正文");
	private JTextField addressertxt = new JTextField(20);
	private JTextField receivertxt = new JTextField(20);
	private JTextField themetxt = new JTextField(20);
	private JTextArea mainbodytxt = new JTextArea();
	private JPanel bg= new JPanel();
	private JPanel panel = new JPanel();
	private List<String> strings = new ArrayList<>();
	//以下两个是实现把email直接赋在该页面上
	private Draft draft;
	public writemail(Draft draft){
		com.garylee.view2.writemail writemail = new com.garylee.view2.writemail();
		writemail.receivertxt.setText(draft.getFrom());
		writemail.themetxt.setText(draft.getTitle());
		writemail.mainbodytxt.setText(draft.getContent());
		writemail.addressertxt.setText(draft.getTo());
	}
	public writemail(Email email){
		com.garylee.view2.writemail writemail = new com.garylee.view2.writemail();
		writemail.receivertxt.setText(email.getFrom());
		writemail.themetxt.setText(email.getTitle());
		writemail.mainbodytxt.setText(email.getContent());
		writemail.addressertxt.setText(email.getTo());
	}
	public writemail(Deleted deleted){
		com.garylee.view2.writemail writemail = new com.garylee.view2.writemail();
		writemail.receivertxt.setText(deleted.getTo());
		writemail.themetxt.setText(deleted.getTitle());
		writemail.mainbodytxt.setText(deleted.getContent());
		writemail.addressertxt.setText(deleted.getFrom());
	}
	public writemail(){
		ImageIcon img = new ImageIcon("src/bg/write.png");
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(panel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		bg.setBounds(0, 0, 850, 600);
		bg.setBackground(Color.white);
		//
		panel.setBounds(0, -5, 850, 600);
		panel.setOpaque(false);
		panel.add(imgLabel);
		
		sendbtn.setBounds(17,55, 89, 37);
		sendbtn.setBorderPainted(false);
		sendbtn.setBackground(Color.white);
		sendbtn.setOpaque(false);
		
		draftbtn.setBounds(113,55, 87, 37);
		draftbtn.setBorderPainted(false);
		draftbtn.setBackground(Color.white);
		draftbtn.setOpaque(false);
		
		cancelbtn.setBounds(206,55, 71, 37);
		cancelbtn.setBorderPainted(false);
		cancelbtn.setBackground(Color.white);
		cancelbtn.setOpaque(false);
		
		timesend.setBounds(283,55, 111, 37);
		timesend.setBorderPainted(false);
		timesend.setBackground(Color.white);
		timesend.setOpaque(false);
		
		addresser.setBounds(30, 115, 100, 40);
		addresser.setFont(new Font("微软雅黑",1,16));
		addresser.setForeground(new Color(200,107,107));
		addressertxt.setBounds(95, 120,300, 35);
		//设置默认值为当前用户邮箱，并设置无法修改
		addressertxt.setText(login.user.getUser());
		addressertxt.setEditable(false);

		receiver.setBounds(30, 165, 100, 40);
		receiver.setFont(new Font("微软雅黑",1,16));
		receivertxt.setBounds(95, 170,300, 35);
		
		theme.setBounds(45, 215, 100, 40);
		theme.setFont(new Font("微软雅黑",1,16));
		themetxt.setBounds(95, 220,380, 35);
		
		addenclosure.setBounds(500, 222, 85, 31);
		addenclosure.setIcon(new ImageIcon("src/bg/add.png"));
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
		mainbody.setBounds(45, 265, 100, 40);
		mainbody.setFont(new Font("微软雅黑",1,16));
		mainbodytxt.setBounds(95, 270, 600, 320);
		mainbodytxt.setBorder(new LineBorder(new Color(122,138,153), 1, false));
		
		
		this.setLayout(null);
		this.setSize(855, 630);
		this.setLocation(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);

		this.add(addresser);
		this.add(addressertxt);
		this.add(receiver);
		this.add(receivertxt);
		this.add(theme);
		this.add(themetxt);
		this.add(addenclosure);
		enclosure.setBounds(590, 222,1000,30);
		this.add(enclosure);
		this.add(mainbody);
		this.add(mainbodytxt);
		this.add(sendbtn);
		this.add(draftbtn);
     	this.add(cancelbtn);
		this.add(timesend);
		this.add(panel);
		this.add(bg);
		cancelbtn.addActionListener(this);
		sendbtn.addActionListener(this);
		timesend.addActionListener(this);
		draftbtn.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String to = receivertxt.getText();
		String title = themetxt.getText();
		String content = mainbodytxt.getText();
		String from = addressertxt.getText();
		if(e.getSource()==cancelbtn){
			setVisible(false);
			new com.garylee.view2.homepage();
		}else if(e.getSource()==sendbtn){
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
				new InBoxUtil(com.garylee.view2.login.user).send(email);
				new SmailDao().addSmail(email);
				System.out.println("发送成功");
				new com.garylee.view2.feedback();
				setVisible(false);
			} catch (ParseException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==timesend){
			Email email = new Email();
			email.setTo(to);
			email.setTitle(title);
			email.setContent(content);
			email.setFrom(from);
			email.setAttachment(strings);
			new timesend(email);
		}else if(e.getSource()==draftbtn){
			new DraftsDao().addDrafts(new Draft(from,to,title,content));
			JOptionPane.showMessageDialog(null, "存草稿成功");
		}

	}
	public static void main(String[] args) {
		new writemail();
	}
}
