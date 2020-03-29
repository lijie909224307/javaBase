package com.UDP.demo1Test;

import java.io.IOException;
import java.net.*;

/**
 * @author lijie
 * @version 1.00
 * @Description: UDP发送端
 * @date 2020/3/23 20:52
 */
public class Send {
    public static void main(String[] args) throws IOException {
        // 1,创建 DatagramSocket ,随机端口号
        DatagramSocket socket = new DatagramSocket();
        // 2,创建 DatagramPacket , 指定数据,长度,地址,端口
        String str = "What are you 弄啥捏?";
        DatagramPacket packet = new DatagramPacket(str.getBytes(),str.getBytes().length,InetAddress.getByName("127.0.0.1"),666);
        // 使用 DatagramSocket 发送 DatagramPacket
        socket.send(packet);
        // 关闭IO流
        socket.close();
    }
}
