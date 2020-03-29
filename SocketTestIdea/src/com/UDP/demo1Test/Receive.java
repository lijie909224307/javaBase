package com.UDP.demo1Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author lijie
 * @version 1.00
 * @Description: UDP接收端
 * @date 2020/3/23 20:57
 */
public class Receive {
    public static void main(String[] args) throws IOException {
        // 1,创建 DatagramSocket , 指定端口号
        DatagramSocket socket = new DatagramSocket(666);
        // 2,创建 DatagramPacket ,指定数组,长度
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        // 3,使用 DatagramSocket 接收 DatagramPacket
        socket.receive(packet);
        // 4,从 DatagramPacket 中获取数据
        byte[] data = packet.getData();
        // 获取有效的字节个数.
        int length = packet.getLength();
        String s = new String(data, 0,length);
        System.out.println(s);
        // 5,关闭 DatagramSocket
        socket.close();
    }
}
