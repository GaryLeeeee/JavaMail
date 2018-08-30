package com.garylee.view;

import com.garylee.dao.DeletedDao;
import com.garylee.dao.DraftsDao;
import com.garylee.dao.EmailDao;
import com.garylee.domain.*;
import com.garylee.util.FileUtil;
import com.garylee.util.UpdateUtil;

import java.awt.Color;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class inbox extends JFrame{
	public static String date = null;
	private JButton delete = new JButton();
	private JButton transpond = new JButton();
	private JButton refresh = new JButton();
	static EmailTable emailTable = new EmailTable();
//	private JTable table = new JTable(13,3);  //发件人,主题,时间
	private static JTable table = new JTable(emailTable);  //发件人,主题,时间
	private JPanel bg= new JPanel();
	private JPanel panel = new JPanel();
	private JPanel tablePane = new JPanel();//

	public inbox(){
		ImageIcon img = new ImageIcon("inbox.png");
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(panel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		bg.setBounds(0, 0, 1097, 600);
		bg.setBackground(Color.white);
		
		panel.setBounds(0, 0, 1097, 600);
		panel.add(imgLabel);
		panel.setOpaque(false);
		
		delete.setBounds(5, 59, 68, 32);
		delete.setBorderPainted(false);
		delete.setBackground(Color.white);
		delete.setOpaque(false);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					int num = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					//
					int isDrop = JOptionPane.showConfirmDialog(null, "确认删除?");
					if (isDrop == JOptionPane.YES_OPTION) {
						System.out.println(num);
						//添加已删除
						new DeletedDao().addReceive(new EmailDao().getOne(num));
						//删除收件箱
						new EmailDao().delEmail(num);
					}
				}
				// 刷新table内容
				emailTable.emails = new EmailDao().list(login.user);
				table.updateUI();
			}
		});
		
		transpond.setBounds(86, 59, 68, 32);
		transpond.setBorderPainted(false);
		transpond.setBackground(Color.white);
		transpond.setOpaque(false);
		transpond.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					int num = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					new writemail(new EmailDao().getOne(num));
				}
			}
		});
		//更新本地数据库与邮箱服务器上的数据为一致
		refresh.setBounds(840, 56, 34, 35);
		refresh.setBorderPainted(false);
		refresh.setBackground(Color.white);
		refresh.setOpaque(false);
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateUtil().updateInbox(login.user);
				//刷新一下table
				emailTable.emails = new EmailDao().list(login.user);
				table.updateUI();
			}
		});

		table.setBounds(5,105, 785, 455);
		table.setRowHeight(35);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int clickCount = e.getClickCount();
				if(clickCount == 2) {
					if (table.getSelectedRow() != -1) {
						int num = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());

						Email email = new EmailDao().getOne(num);
						inbox.date = email.getTime();
						new lookmail(email);
					}
				}
			}
		});
//		table.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));

		tablePane.setLayout(null);//
		JScrollPane scrollPane = new JScrollPane();//
		scrollPane.setViewportView(table);//
		scrollPane.setBounds(0, 0, 885, 455);//
		tablePane.add(scrollPane);//
		tablePane.setBounds(5, 105, 885, 455);//

		this.setLayout(null);
		this.setSize(900, 600);
		this.setLocation(400, 300);
		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//只关闭当前窗体
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		this.add(delete);
		this.add(transpond);
		this.add(refresh);
//		this.add(table);
		this.add(tablePane);
		this.add(panel);
		this.add(bg);
	}
	
	public static void main(String[] args) {
		new inbox();
	}
}
