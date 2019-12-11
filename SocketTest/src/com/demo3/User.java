package com.demo3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
//客户端
public class User {
	public static void main(String[] args) {
		try {
			
			//1 创建一个用户的用户信息
			Info info1 = new Info("希拉里", 19, "男");				
			//2 建立连接 .  网络ID为本机,端口随意定为 8080,有了socket就可以得到IO流
			Socket socket = new Socket("127.0.0.1",8088);			
			//3 创建输出流,向服务器发送信息
			OutputStream os = socket.getOutputStream();				
			//4 包装成对象输出流,因为要发送的信息为对象.
			ObjectOutputStream oos = new ObjectOutputStream(os);	
			//5 输出用户信息..
			oos.writeObject(info1);									
			//6 关闭客户端单方输出流
			socket.shutdownOutput();								
			//7 创建输入流对象,用于接收服务器端的信息(回复)
			InputStream is = socket.getInputStream();			
			//8 拟定服务器回复一个字符串,用于接收
			String reply = null;								
			//9 方便输出,用带有缓冲区的字符输入流读取信息
			BufferedReader br = new BufferedReader(new InputStreamReader(is));		
			//10 如果接收到信息(不为空),就打印输出
			while((reply=br.readLine())!=null){			
				System.out.println(reply);
			}
			//11 关闭流与Socket
			socket.close();								//11
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
