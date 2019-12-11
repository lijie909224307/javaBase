package com.demo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Customer {
	public static void main(String[] args) {
			try {
				Socket socket = new Socket("localhost", 8800);
				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				String msg1 = "hello world!";
				out.write(msg1.getBytes());
				socket.shutdownOutput();
				
				String reply = null;
				BufferedReader br =  new BufferedReader( new InputStreamReader(in) );
				while( (reply = br.readLine() )!=null){
					System.out.println(reply);
				}
				socket.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
	}
}

