package com.garylee.view2;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class feedback extends JFrame{
	private JButton rehome = new JButton();
	private JButton again = new JButton();
	private JPanel bg= new JPanel();
	private JPanel panel = new JPanel();
	
	public feedback(){
		
		ImageIcon img = new ImageIcon("src/bg/feedback.png");
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(panel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		bg.setBounds(0, 0, 760, 400);
		bg.setBackground(Color.white);
		//
		panel.setBounds(0, 0, 760, 400);
		panel.setOpaque(false);
		panel.add(imgLabel);
		
		rehome.setBounds(152, 235, 196, 48);
		rehome.setBorderPainted(false);
		rehome.setBackground(Color.white);
		rehome.setOpaque(false);
		
		again.setBounds(354, 235, 148, 48);
		again.setBorderPainted(false);
		again.setBackground(Color.white);
		again.setOpaque(false);
		
		this.setLayout(null);
		this.setSize(760, 400);
		this.setLocation(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		this.add(rehome);
		this.add(again);
		this.add(panel);
		this.add(bg);
	}
	public static void main(String[] args) {
		new feedback();
	}
}
