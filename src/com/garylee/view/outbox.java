package com.garylee.view;

import com.garylee.dao.DraftsDao;
import com.garylee.dao.SmailDao;
import com.garylee.domain.SmailTable;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class outbox extends JFrame{
	private JButton delete = new JButton();
	private JButton transpond = new JButton();
//	private JTable table = new JTable(13,3);  //
	SmailTable smailTable = new SmailTable();
	private JTable table = new JTable(smailTable);  //
	private JPanel bg= new JPanel();
	private JPanel panel = new JPanel();
	private JPanel tablePane = new JPanel();//

	public outbox(){
		ImageIcon img = new ImageIcon("outbox.png");
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
		
		transpond.setBounds(86, 59, 68, 32);
		transpond.setBorderPainted(false);
		transpond.setBackground(Color.white);
		transpond.setOpaque(false);
		transpond.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					int num = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					new writemail(new SmailDao().getOne(num));
				}
			}
		});
		
		table.setBounds(5,105, 885, 455);
		table.setRowHeight(35);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
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
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		this.add(delete);
		this.add(transpond);
//		this.add(table);
		this.add(tablePane);
		this.add(panel);
		this.add(bg);
	}
	public static void main(String[] args) {
		new outbox();
	}
}
