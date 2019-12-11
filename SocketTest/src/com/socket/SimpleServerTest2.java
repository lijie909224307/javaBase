package com.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleServerTest2 {
	public static void main(String[] args) {
		Socket socket = null ;
		ServerSocket server = null ;
		try {
			server = new ServerSocket(5000);
			int i = 1;
			while(true){
				System.out.println(i);
				Socket ser = server.accept();
				PrintWriter writer = new PrintWriter(ser.getOutputStream());
				writer.println("你是第"+i+"个连接的帅哥!");
				writer.flush();
				writer.close();
				i++;
			}
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				server.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
}
