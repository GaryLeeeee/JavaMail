package com.garylee.view;

import com.garylee.dao.DeletedDao;
import com.garylee.dao.DraftsDao;
import com.garylee.domain.Draft;
import com.garylee.domain.DraftTable;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class drafts extends JFrame{
	private JButton dedraft = new JButton();
	private JButton write = new JButton();
	DraftTable draftTable = new DraftTable();
//	private JTable table = new JTable(13,3);  //
	private JTable table = new JTable(draftTable);  //
	private JPanel bg= new JPanel();
	private JPanel panel = new JPanel();
	private JPanel tablePane = new JPanel();//

	public drafts(){
		ImageIcon img = new ImageIcon("draft.png");
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(panel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		bg.setBounds(0, 0, 1040, 600);
		bg.setBackground(Color.white);

		panel.setBounds(0, 0, 1040, 600);
		panel.add(imgLabel);
		panel.setOpaque(false);

		dedraft.setBounds(6, 60, 105, 35);
		dedraft.setBorderPainted(false);
		dedraft.setBackground(Color.white);
		dedraft.setOpaque(false);
		dedraft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 进行是否选择row判断
				if (table.getSelectedRow() != -1) {
					int num = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					// 删除sc信息(如果已选过该课程)
					int isDrop = JOptionPane.showConfirmDialog(null, "确认删除?");
					if (isDrop == JOptionPane.YES_OPTION) {
						Draft draft= new DraftsDao().getOne(num);
						//删除草稿箱
						new DraftsDao().delDraft(num);
						//添加已删除
						new DeletedDao().addDraft(draft);
					}
				}
				// 刷新table内容
				draftTable.drafts = new DraftsDao().listDrafts();
				table.updateUI();
			}
		});

		write.setBounds(114, 60, 73, 35);
		write.setBorderPainted(false);
		write.setBackground(Color.white);
		write.setOpaque(false);
		write.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					int num = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
						new writemail(new DraftsDao().getOne(num));
				}

			}
		});

		table.setBounds(5,109, 885, 455);
		table.setRowHeight(35);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(350);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int clickCount = e.getClickCount();
				if(clickCount == 2)
					System.out.println(2);
			}
		});
//		table.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));

		tablePane.setLayout(null);//
		JScrollPane scrollPane = new JScrollPane();//
		scrollPane.setViewportView(table);//
		scrollPane.setBounds(0, 0, 885, 455);//
		tablePane.add(scrollPane);//
		tablePane.setBounds(5, 109, 885, 455);//

		this.setLayout(null);
		this.setSize(900, 600);
		this.setLocation(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);

		this.add(dedraft);
		this.add(write);
//		this.add(table);
		this.add(tablePane);
		this.add(panel);
		this.add(bg);
	}
	public static void main(String[] args) {
		new drafts();
	}
}
