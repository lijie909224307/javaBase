package com.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleTest1 {
	public static void main(String[] args) {
		Socket socket = null ;
		ServerSocket server = null ;
		try {
			server = new ServerSocket(8069);			
			socket = new Socket("127.0.0.1",8069);
			
			InputStreamReader stream = new InputStreamReader(socket.getInputStream());
			
			BufferedReader reader = new BufferedReader(stream);
			
			String msg = reader.readLine();
			
			System.out.println(msg);
			
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
