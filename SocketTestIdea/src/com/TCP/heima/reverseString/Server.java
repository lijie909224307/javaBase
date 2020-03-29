package com.TCP.heima.reverseString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lijie
 * @version 1.00
 * @Description: TODO
 * @date 2020/3/29 21:48
 */
public class Server {
    public static void main(String[] args) throws IOException {


        ServerSocket server = new ServerSocket(54321);
        System.out.println("服务器启动.绑定54321端口");

        while (true){
            final Socket socket = server.accept();
            // 开启一条线程.
            new Thread(){
                public void run() {
                    try {
                        // 获取输入流
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        // 获取输出流
                        PrintStream printStream = new PrintStream(socket.getOutputStream());
                        String line = bufferedReader.readLine();
                        // 反转字符串并写回去
                        line = new StringBuilder(line).reverse().toString();
                        printStream.print(line);

                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }.start();

        }




    }
}
