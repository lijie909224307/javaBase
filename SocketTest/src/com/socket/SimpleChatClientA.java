package com.socket;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleChatClientA {
	
	// 发送信息框
	JTextField outGoing ;
	// 输出流
	PrintWriter writer;
	Socket sock;
	
	public void go(){
		JFrame frame = new JFrame("Lijie Simple Chat Client");
		JPanel mainPanel = new JPanel();
		outGoing = new JTextField(20);
		JButton sendbutton = new JButton("Send");
		
		sendbutton.addActionListener(new SendButtonListener());
		mainPanel.add(outGoing);
		mainPanel.add(sendbutton);
		frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
		setUpNetworking();
		frame.setSize(400,500);
		frame.setVisible(true);
	}

	private void setUpNetworking() {
		try {
			sock = new Socket("127.0.0.1",5000);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("networking established");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public class SendButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				writer.println(outGoing.getText());
				writer.flush();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			outGoing.setText("");
			outGoing.requestFocus();
		}
	}
	
	public static void main(String[] args) {
		new SimpleChatClientA().go();
	}
	
}
