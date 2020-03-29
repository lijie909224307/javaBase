package com.UDP.demo3Thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * @author lijie
 * @version 1.00
 * @Description: TODO
 * @date 2020/3/23 21:23
 */
public class Send extends Thread{
    @Override
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket();
            Scanner scanner = new Scanner(System.in);
            String msg;
            while (true){
                msg = scanner.nextLine();
                if("quit".equals(msg)){
                    break;
                }
                DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length,InetAddress.getByName("127.0.0.1"),666);
                socket.send(packet);
            }
            socket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Receive().start();
        sleep(200);
        new Send().start();
    }
}


class Receive extends Thread{
    @Override
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(666);
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            while (true){
                socket.receive(packet);
                String ip = packet.getAddress().getHostAddress();
                String msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println(ip + ":" + msg);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

