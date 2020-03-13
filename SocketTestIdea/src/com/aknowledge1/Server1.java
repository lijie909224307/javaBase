package com.aknowledge1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lijie
 * @version 1.00
 * @Description: TODO
 * @date 2020-03-13 14:25
 */
public class Server1 {
    public static void main(String[] args) throws IOException {
        // 容量
        int size = 3;
        ServerSocket server = new ServerSocket(5566, size);
        System.out.println("服务器建立完毕,正在监听 5566 端口...");
        while (size > 0){
            Socket socket = server.accept();
            size -= 1;
            System.out.println("发现敌机!!!!!" + socket);
            System.out.println("继续监听中....");
        }
        System.out.println("MAY DAY MAY DAY !!!");
        server.close();
    }
}
