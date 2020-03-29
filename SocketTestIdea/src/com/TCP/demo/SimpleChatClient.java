package com.TCP.demo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


// 客户端
public class SimpleChatClient {

	JTextArea incoming;
	JTextField outgoing;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Socket sock;
	
	public static void main(String[] args) {
		SimpleChatClient client = new SimpleChatClient();
		client.go();
	}
	
	
	public void go(){
		JFrame frame = new JFrame("lijie simple demo client");
		JPanel mainPanel = new JPanel();
		incoming = new JTextArea(20,50); 
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(true);
		JScrollPane qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		outgoing = new JTextField(20);
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new SendButtonListener());
		mainPanel.add(qScroller);
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		
		setUpNetworking();
		
		Thread readerThread = new Thread(new IncomingReader(incoming,ois));
		readerThread.start();

		
		frame.getContentPane().add(BorderLayout.CENTER , mainPanel);
		frame.setSize(600,500);
		frame.setVisible(true);
	}

	private void setUpNetworking() {
		try {
			sock = new Socket("127.0.0.1",4001);
			ois = new ObjectInputStream(sock.getInputStream());
			oos = new ObjectOutputStream(sock.getOutputStream());
			System.out.println("network established");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public class SendButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Info info = new Info();
				info.setMsg(outgoing.getText());
				info.setFromWho(sock.getLocalAddress().getHostAddress());
				info.setDate(new Date());
				oos.writeObject(info);
				oos.flush();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			outgoing.setText("");
			outgoing.requestFocus();
		}
	}
	
}
