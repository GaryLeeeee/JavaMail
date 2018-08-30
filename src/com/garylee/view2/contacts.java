package com.garylee.view2;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

public class contacts extends JFrame{
	private JButton writebtn = new JButton();
	private JButton deletebtn = new JButton();
	private JTable table = new JTable(12,2);
	private JPanel bg= new JPanel();
	private JPanel panel = new JPanel();
	
	public contacts(){
		ImageIcon img = new ImageIcon("src/bg/contacts.png");
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(panel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		bg.setBounds(0, 0, 800, 600);
		bg.setBackground(Color.white);
		
		panel.setBounds(0, -5, 800, 600);
		panel.add(imgLabel);
		panel.setOpaque(false);
		
		writebtn.setBounds(12, 56, 72, 36);
		writebtn.setBorderPainted(false);
		writebtn.setBackground(Color.white);
		writebtn.setOpaque(false);
		
		deletebtn.setBounds(90, 56, 70, 36);
		deletebtn.setBorderPainted(false);
		deletebtn.setBackground(Color.white);
		deletebtn.setOpaque(false);
		
		table.setBounds(5,115, 790, 420);
		table.setRowHeight(35);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(320);
		table.setBorder(new LineBorder(new Color(122,138,153), 1, false));
		
		this.setLayout(null);
		this.setSize(805, 575);
		this.setLocation(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		this.add(writebtn);
		this.add(deletebtn);
		this.add(table);
		this.add(panel);
		this.add(bg);
	}
	public static void main(String[] args) {
		new contacts();
	}
}
