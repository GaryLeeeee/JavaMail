package com.garylee.view2;

import com.garylee.dao.SmailDao;
import com.garylee.domain.SmailTable;
import com.garylee.view.login;
import com.garylee.view.writemail;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class outbox extends JFrame{
	private JButton delete = new JButton();
	private JButton transpond = new JButton();
	SmailTable smailTable = new SmailTable();
	private JTable table = new JTable(smailTable);  //
	private JPanel bg= new JPanel();
	private JPanel panel = new JPanel();
	private JPanel tablePane = new JPanel();//
	public outbox(){
		ImageIcon img = new ImageIcon("src/bg/outbox.png");
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(panel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		bg.setBounds(0, 0, 1000, 600);
		bg.setBackground(Color.white);
		
		panel.setBounds(0, -5, 1000, 600);
		panel.add(imgLabel);
		panel.setOpaque(false);
		
		delete.setBounds(0, 45, 107, 46);
		delete.setBorderPainted(false);
		delete.setBackground(Color.white);
		delete.setOpaque(false);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					int num = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					// 删除sc信息(如果已选过该课程)
					int isDrop = JOptionPane.showConfirmDialog(null, "确认删除?");
					if (isDrop == JOptionPane.YES_OPTION) {
						new SmailDao().delSmail(num);
					}
				}
				// 刷新table内容
				smailTable.emails = new SmailDao().listSmail(login.user);
				table.updateUI();
			}
		});
		transpond.setBounds(110, 45, 107, 46);
		transpond.setBorderPainted(false);
		transpond.setBackground(Color.white);
		transpond.setOpaque(false);
		transpond.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					int num = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					new com.garylee.view2.writemail(new SmailDao().getOne(num));
				}
			}
		});
		table.setBounds(5,100, 920, 455);
		table.setRowHeight(35);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(320);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.setBorder(new LineBorder(new Color(122,138,153), 1, false));

		tablePane.setLayout(null);//
		JScrollPane scrollPane = new JScrollPane();//
		scrollPane.setViewportView(table);//
		scrollPane.setBounds(0, 0, 885, 455);//
		tablePane.add(scrollPane);//
		tablePane.setBounds(5, 105, 885, 455);//

		this.setLayout(null);
		this.setSize(935, 595);
		this.setLocation(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		this.add(delete);
		this.add(transpond);
		this.add(tablePane);
		this.add(panel);
		this.add(bg);
	}
	public static void main(String[] args) {
		new outbox();
	}
}
