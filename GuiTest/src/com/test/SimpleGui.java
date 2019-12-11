package com.test;


import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SimpleGui {
	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		JButton jButton = new JButton("click me");
		
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.getContentPane().add(jButton);
		jFrame.setSize(300, 300);
		jFrame.setVisible(true);
		
		
	}
}
