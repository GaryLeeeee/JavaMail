package com.garylee.view;

import com.garylee.dao.DraftsDao;
import com.garylee.dao.EmailDao;
import com.garylee.dao.SmailDao;
import com.garylee.dao.UserDao;
import com.garylee.domain.User;
import com.garylee.domain.UserTable;
import com.garylee.util.RegisterUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class manager extends JFrame{
	private JButton add = new JButton("添加用户");
	private JButton alter = new JButton("修改用户");
	private JButton delete = new JButton("删除用户");
	private JButton search = new JButton();
	private JTextField searchtxt = new JTextField(10);
	private JLabel sum = new JLabel("统计邮件");
	private JLabel sent = new JLabel("已发送: ");
	private JLabel received = new JLabel("已收到: ");
//	private JTable table = new JTable(11,2);  //用户帐号,密码
	static UserTable userTable = new UserTable();
	private static JTable table = new JTable(userTable);  //用户帐号,密码
	private JPanel bg= new JPanel();
	private JPanel panel = new JPanel();
	private JPanel tablePane = new JPanel();//

	public manager(){
		ImageIcon img = new ImageIcon("admin.png");
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(panel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		bg.setBounds(0, 0, 900, 540);
		bg.setBackground(Color.white);
		
		panel.setBounds(0, 0, 900, 540);
		panel.add(imgLabel);
		panel.setOpaque(false);
		
		add.setBounds(20, 55, 100, 30);
		add.setFont(new Font("微软雅黑",1,16));
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddDialog(new JFrame());// visible
			}
		});
		
		alter.setBounds(130, 55, 100, 30);
		alter.setFont(new Font("微软雅黑",1,16));
		alter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					EditDialog editDialog = new EditDialog(new JFrame());//
					editDialog.textField1.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					editDialog.textField2.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					editDialog.textField3.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				}
			}
		});
		
		delete.setBounds(240, 55, 100, 30);
		delete.setFont(new Font("微软雅黑",1,16));
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					int num = (int) table.getValueAt(table.getSelectedRow(), 0);
					int isDrop = JOptionPane.showConfirmDialog(null, "确认删除?");
					if (isDrop == JOptionPane.YES_OPTION) {
						new UserDao().delete(num);
						// 刷新table内容
						userTable.users = new UserDao().listUser();
						table.updateUI();
					}
				}
			}
		});
		
		sum.setFont(new   java.awt.Font("Dialog",1,25));
		sum.setBounds(720,120, 120, 50);
		
		sent.setFont(new   java.awt.Font("Dialog",1,18));
		sent.setBounds(690,170, 120, 50);
		
		received.setFont(new   java.awt.Font("Dialog",1,18));
		received.setBounds(690,205, 120, 50);
		
		searchtxt.setBounds(615, 40, 200, 34);
		
		search.setBounds(815, 40, 34, 34);
		search.setBorderPainted(false);
		search.setBackground(Color.white);
		search.setOpaque(false);
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = searchtxt.getText();
				if (name != null || ("").equals(name)) {
					// 刷新table内容
					userTable.users = new UserDao().search(name);
					table.updateUI();
				}
			}
		});
		
		table.setBounds(5, 100, 650, 385);
		table.setRowHeight(35);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(350);
		table.getColumnModel().getColumn(2).setPreferredWidth(350);
//		table.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));

		tablePane.setLayout(null);//
		JScrollPane scrollPane = new JScrollPane();//
		scrollPane.setViewportView(table);//
		scrollPane.setBounds(0, 0, 650, 385);//
		tablePane.add(scrollPane);//
		tablePane.setBounds(5, 100, 650, 385);//

		this.setLayout(null);
		this.setSize(905, 520);
		this.setLocation(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		this.add(add);
		this.add(alter);
		this.add(delete);
		this.add(sum);
		this.add(sent);
		this.add(received);
		this.add(searchtxt);
		this.add(search);
//		this.add(table);
		this.add(tablePane);
		this.add(panel);
		this.add(bg);

		//设置已发送和已收到数量
		sent.setText(sent.getText()+new SmailDao().hasSent());
		received.setText(received.getText()+new EmailDao().hasReceived());


	}

	// 增加Dialog
	static class AddDialog extends Dialog {
		JLabel label1 = new JLabel("用户名:");
		JLabel label2 = new JLabel("密码:");
		JTextField textField1 = new JTextField(20);
		JTextField textField2 = new JTextField(20);
		JButton button = new JButton("添加");

		AddDialog(JFrame frame) {
			super(frame);
			frame.setSize(250, 300);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			// this.setModal(true);
			// TODO Auto-generated constructor stub
			JPanel addPanel = new JPanel();
			// JPanel addPanel = new JPanel(new GridLayout(2, 2));
			JPanel southPanel = new JPanel();
			addPanel.add(label1);
			addPanel.add(textField1);
			addPanel.add(label2);
			addPanel.add(textField2);
			addPanel.setBounds(50, 20, 200, 100);
			southPanel.add(button);
			frame.add(addPanel, BorderLayout.CENTER);
			frame.add(southPanel, BorderLayout.SOUTH);
			// 提交按钮
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					// 添加一个对象
					String name = textField1.getText();
					String password = textField2.getText();
					// 对输入判断是否为空
					try {
						if(new UserDao().getOne(name,password)!=null){
							JOptionPane.showMessageDialog(null,"用户已存在!无需重复注册!");
						} else if(new RegisterUtil(name,password).isTrue()){
							User user = new User(name,password);
							new UserDao().add(user);
							JOptionPane.showMessageDialog(null,"注册成功");
							frame.setVisible(false);
							//刷新table
							userTable.users = new UserDao().listUser();
							table.updateUI();
						}else {
							JOptionPane.showMessageDialog(null,"用户名或口令错误!");
						}
					} catch (MessagingException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
	}

	// 更新Dialog
	static class EditDialog extends Dialog {
		JLabel label1 = new JLabel("ID:");
		JLabel label2 = new JLabel("用户名:");
		JLabel label3 = new JLabel("密码:");
		JTextField textField1 = new JTextField(20);
		JTextField textField2 = new JTextField(20);
		JTextField textField3 = new JTextField(20);
		JButton button = new JButton("添加");

		EditDialog(JFrame frame) {
			super(frame);
			frame.setSize(250, 310);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			// this.setModal(true);
			// TODO Auto-generated constructor stub
			JPanel addPanel = new JPanel();
			JPanel southPanel = new JPanel();
			addPanel.add(label1);
			textField1.setEnabled(false);
			addPanel.add(textField1);
			addPanel.add(label2);
			addPanel.add(textField2);
			addPanel.add(label3);
			addPanel.add(textField3);
			addPanel.setBounds(50, 20, 200, 100);
			southPanel.add(button);
			frame.add(addPanel, BorderLayout.CENTER);
			frame.add(southPanel, BorderLayout.SOUTH);
			// 提交按钮
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int id = Integer.parseInt(textField1.getText());
					String name = textField2.getText();
					String password = textField3.getText();

						new UserDao().update(new User(id,name,password));
						JOptionPane.showMessageDialog(frame, "提交成功");
						// 刷新table内容
						userTable.users = new UserDao().listUser();
						table.updateUI();
						// frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setVisible(false);
				}
			});
		}

	}
	
	public static void main(String[] args) {
		new manager();
	}
}
