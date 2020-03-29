package com.TCP.demo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

// 服务器
public class SimpleServerTest2 {
	public static void main(String[] args) {
		Socket customSocket = null ;
		ServerSocket server = null ;

		try {
			server = new ServerSocket(4001);

			while(true){
				customSocket = server.accept();

				/*ObjectInputStream ois = new ObjectInputStream(customSocket.getInputStream());
				IncomingReader incomingReader = new IncomingReader();
				incomingReader.setOis(ois);
				Thread readerThread = new Thread(incomingReader);
				readerThread.start();*/

				// 发送消息
				ObjectOutputStream oos = new ObjectOutputStream(customSocket.getOutputStream());
				Info info = new Info();
				info.setFromWho("服务器");
				info.setDate(new Date());
				info.setMsg("   欢迎来自"+customSocket.getLocalAddress().getHostAddress()+"的帅哥!");
				oos.writeObject(info);
				oos.flush();
			}
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	


	
	
	
	
}
