package com.garylee.view2;

import com.garylee.dao.EmailDao;
import com.garylee.domain.EmailTable;
import com.garylee.util.UpdateUtil;
import com.garylee.view.contacts;
import com.garylee.view.deleted;
import com.garylee.view.drafts;
import com.garylee.view.inbox;
import com.garylee.view.login;
import com.garylee.view.outbox;
import com.garylee.view.writemail;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class homepage extends JFrame{
	  private JButton write = new JButton();
	   private JButton received = new JButton();
	   private JButton recebox = new JButton();
	   private JButton draft = new JButton();
	   private JButton sent = new JButton();
	   private JButton deleted = new JButton();
	   private JButton connector = new JButton();
	   private JLabel title = new JLabel("1024");
		private JLabel title2 = new JLabel("邮箱管理系统");
		private JPanel bg= new JPanel();
		private JPanel panel = new JPanel();
	private JButton change = new JButton("★一键换肤");


	public homepage(){
			ImageIcon img = new ImageIcon("src/bg/homepage.png");
			JLabel imgLabel = new JLabel(img);
			this.getLayeredPane().add(panel, new Integer(Integer.MIN_VALUE));
			imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
			
			bg.setBounds(0, 0, 850, 500);
			bg.setBackground(Color.white);	
			
			panel.setBounds(0, -5, 850, 500);
			panel.add(imgLabel);
			panel.setOpaque(false);
			change.setBounds(700, 10, 100, 30);
			change.setBackground(Color.white);
			change.setBorderPainted(false);
			change.setFocusPainted(false);
			change.setOpaque(false);
			change.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new com.garylee.view.homepage();
				}
			});

			 title.setFont(new   java.awt.Font("Dialog",   3,   55));   
			 title.setForeground(new Color(129,154,254));
			 title.setBounds(290, 55, 125, 50);
			 title2.setBounds(420, 60, 200, 50);
			 title2.setFont(new   java.awt.Font("Dialog",   1,   30));   
			 title2.setForeground(new Color(129,154,254));
			 
			 write.setBounds(0,43, 125, 50);
			 write.setBorderPainted(false);
			 write.setBackground(Color.white);
			 write.setOpaque(false);
			write.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
//			 setVisible(false);
					new com.garylee.view2.writemail();
				}
			});
			 received.setBounds(126,43, 123, 50);
			 received.setBorderPainted(false);
			 received.setBackground(Color.white);
			 received.setOpaque(false);
			received.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new UpdateUtil().updateInbox(com.garylee.view.login.user);
					//刷新一下table
					EmailTable.emails = new EmailDao().list(login.user);
				}
			});

			 recebox.setBounds(0,107, 247, 40);
			 recebox.setBorderPainted(false);
			 recebox.setBackground(Color.white);
			 recebox.setOpaque(false);
			recebox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
//			 setVisible(false);
					new com.garylee.view2.inbox();
				}
			});
			 
			 draft.setBounds(0,152, 247, 40);
			 draft.setBorderPainted(false);
			 draft.setBackground(Color.white);
			 draft.setOpaque(false);
			draft.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new com.garylee.view2.drafts();
				}
			});
			 sent.setBounds(0,197, 247, 40);
			 sent.setBorderPainted(false);
			 sent.setBackground(Color.white);
			 sent.setOpaque(false);
			sent.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new com.garylee.view2.outbox();
				}
			});
			 deleted.setBounds(0,243, 247, 40);
			 deleted.setBorderPainted(false);
			 deleted.setBackground(Color.white);
			 deleted.setOpaque(false);
			deleted.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new com.garylee.view2.deleted();
				}
			});
			 connector.setBounds(0,290, 247, 40);
			 connector.setBorderPainted(false);
			 connector.setBackground(Color.white);
			 connector.setOpaque(false);
			connector.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new com.garylee.view2.contacts();
				}
			});
			this.setLayout(null);
			this.setSize(855, 528);
			this.setLocation(400, 300);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.setVisible(true);
		this.add(change);

		this.add(title);
			this.add(title2);
			this.add(write);
			this.add(received);
			this.add(recebox);
			this.add(draft);
			this.add(sent);
			this.add(deleted);
			this.add(connector);
			this.add(panel);
			this.add(bg);
		}
		public static void main(String[] args) {
			new homepage();
		}
}
