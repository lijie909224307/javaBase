package com.inet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author lijie
 * @version 1.00
 * @Description: 使用Socket连接到指定主机
 * @date 2020/3/15 18:56
 */
public class ConnectHost {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("www.manong.com", 80);
        InetAddress address = socket.getInetAddress();

        System.out.println("连接到了 :" + address);

        socket.close();
    }

}
