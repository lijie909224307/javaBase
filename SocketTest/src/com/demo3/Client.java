package com.demo3;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//服务器
public class Client {
	public static void main(String[] args) {
		try {
			
			//1 建立服务器连接
			ServerSocket server = new ServerSocket(8088);		
			//2 开始监听,返回值为Socket类型,及代表可能有IO流
			Socket socket = server.accept();					
			//3 创建输入流对象,用于接收客户端发来的信息
			InputStream is = socket.getInputStream();			
			//4 由于知道是对象流,所以将输入流包装成可序列化对象流
			ObjectInputStream ois = new ObjectInputStream(is);	
			//5 强制将接收到的Object类型转换成 Info 类型
			Info msg = (Info) ois.readObject();					
			//6 读取接收道德信息,由于直接重写了 toString方法,直接打印输出
			if(msg!=null){									
				System.out.println(msg);
			}
			//7 对上面接收到的信息做出判断,选择回应的字符串
			String reply = null;
			if(msg.getName().equals("希拉里")){
				reply = "correct!";
			}else{
				reply = "Fuck you!";
			}
			//8 做出回应
			OutputStream os = socket.getOutputStream();		
//			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			os.write(reply.getBytes());
//			bw.write(reply);
			
			//9 关闭资源
			socket.shutdownOutput();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
