package com.garylee.view2;

import com.garylee.dao.DeletedDao;
import com.garylee.dao.DraftsDao;
import com.garylee.dao.EmailDao;
import com.garylee.domain.Deleted;
import com.garylee.domain.DeletedTable;
import com.garylee.domain.Draft;
import com.garylee.domain.Email;
import com.garylee.view.writemail;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class deleted extends JFrame{
	private JButton delete = new JButton();
	private JButton transpond = new JButton();
	private JButton restore = new JButton();
	DeletedTable deletedTable = new DeletedTable();
	private JTable table = new JTable(deletedTable);
	private JPanel bg= new JPanel();
	private JPanel panel = new JPanel();
	private JPanel tablePane = new JPanel();//

	public deleted(){
		ImageIcon img = new ImageIcon("src/bg/deleted.png");
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(panel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		bg.setBounds(0, 0, 1000, 600);
		bg.setBackground(Color.white);
		
		panel.setBounds(0, -5, 1000, 600);
		panel.add(imgLabel);
		panel.setOpaque(false);
		
		delete.setBounds(0, 43, 134, 47);
		delete.setBorderPainted(false);
		delete.setBackground(Color.white);
		delete.setOpaque(false);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 进行是否选择row判断
				if (table.getSelectedRow() != -1) {
					int num = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					// 删除sc信息(如果已选过该课程)
					int isDrop = JOptionPane.showConfirmDialog(null, "确认删除?");
					if (isDrop == JOptionPane.YES_OPTION) {
						//删除已删除
						new DeletedDao().delDeleted(num);

					}
				}
				// 刷新table内容
				deletedTable.deleteds = new DeletedDao().listDeleted();
				table.updateUI();
			}
		});
		transpond.setBounds(138, 43, 103, 47);
		transpond.setBorderPainted(false);
		transpond.setBackground(Color.white);
		transpond.setOpaque(false);
		restore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					int num = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					new com.garylee.view2.writemail(new DeletedDao().getOne(num));
				}
			}
		});
		restore.setBounds(245, 43, 103, 47);
		restore.setBorderPainted(false);
		restore.setBackground(Color.white);
		restore.setOpaque(false);
		transpond.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					int num = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					// 删除sc信息(如果已选过该课程)
					int isDrop = JOptionPane.showConfirmDialog(null, "确认恢复?");
					if (isDrop == JOptionPane.YES_OPTION) {
						Deleted deleted = new DeletedDao().getOne(num);
						//判断是从哪删除的
						if(deleted.getType().equals("草稿箱")){
							Draft draft = new Draft(deleted.getFrom(),deleted.getTo(),deleted.getTitle(),deleted.getContent());
							new DraftsDao().addDrafts(draft);
							//删除已删除
							new DeletedDao().delDeleted(num);
						}else if(deleted.getType().equals("收件箱")){
							Email email = new Email(deleted.getTitle(),deleted.getContent(),deleted.getTime(),deleted.getFrom(),deleted.getTo());
							try {
								new EmailDao().add(email);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							//删除已删除
							new DeletedDao().delDeleted(num);
						}else if(deleted.getType().equals("已发送")){
							Draft draft = new Draft(deleted.getFrom(),deleted.getTo(),deleted.getTitle(),deleted.getContent());
							new DraftsDao().addDrafts(draft);
							//删除已删除
							new DeletedDao().delDeleted(num);
						}



					}
				}
				// 刷新table内容
				deletedTable.deleteds = new DeletedDao().listDeleted();
				table.updateUI();
			}
		});
		table.setBounds(5,105, 885, 455);		table.setRowHeight(35);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
//		table.setBorder(new LineBorder(new Color(0,0,0), 1, false));

		tablePane.setLayout(null);//
		JScrollPane scrollPane = new JScrollPane();//
		scrollPane.setViewportView(table);//
		scrollPane.setBounds(0, 0, 885, 455);//
		tablePane.add(scrollPane);//
		tablePane.setBounds(5, 105, 885, 455);//
		
		this.setLayout(null);
		this.setSize(935, 594);
		this.setLocation(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		this.add(delete);
		this.add(transpond);
		this.add(restore);
		this.add(tablePane);
		this.add(panel);
		this.add(bg);
	}
	public static void main(String[] args) {
		new deleted();
	}
}
