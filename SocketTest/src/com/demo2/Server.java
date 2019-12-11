package com.demo2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8800);
			Socket accept = server.accept();
			InputStream in = accept.getInputStream();
			OutputStream out = accept.getOutputStream();
			
			BufferedReader br =  new BufferedReader( new InputStreamReader(in) );
			String info = null;
			while( (info = br.readLine() )!=null){
				System.out.println(info);
			}
			String reply = "copy that!";
			out.write(reply.getBytes());
			accept.shutdownOutput();
			server.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
