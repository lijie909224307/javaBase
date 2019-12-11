package com.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClientTest2 {
	public static void main(String[] args) {
		Socket socket = null ;
		try {
			socket = new Socket("127.0.0.1",5526);
			InputStreamReader stream = new InputStreamReader(socket.getInputStream());
			BufferedReader reader = new BufferedReader(stream);
			String msg = reader.readLine();
			reader.close();
			System.out.println(msg);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
}
