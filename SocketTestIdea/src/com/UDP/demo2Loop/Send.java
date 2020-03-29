package com.UDP.demo2Loop;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @author lijie
 * @version 1.00
 * @Description: TODO
 * @date 2020/3/23 21:09
 */
public class Send {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket packet;
        while (true){
            String msg = scanner.nextLine();
            if("quit".equals(msg)){
                break;
            }
            packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName("127.0.0.1"), 666);
            socket.send(packet);
        }
        socket.close();
    }
}
