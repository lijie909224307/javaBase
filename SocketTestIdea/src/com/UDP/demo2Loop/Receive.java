package com.UDP.demo2Loop;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author lijie
 * @version 1.00
 * @Description: TODO
 * @date 2020/3/23 21:13
 */
public class Receive {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(666);
        DatagramPacket packet = new DatagramPacket(new byte[1024],1024);
        while (true){
            socket.receive(packet);
            byte[] data = packet.getData();
            int length = packet.getLength();
            String msg = new String(data,0,length);
            String ip = packet.getAddress().getHostAddress();
            System.out.println(ip + ":" + msg);
        }
    }
}
