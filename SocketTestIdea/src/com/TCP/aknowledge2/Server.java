package com.TCP.aknowledge2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lijie
 * @version 1.00
 * @Description: TODO
 * @date 2020/3/15 17:44
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Socket socket = serverSocket.accept();
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        String s = in.readUTF();
        System.out.println(s);

        out.writeUTF("好的,客户端,我是服务器端,");

        s = in.readUTF();
        System.out.println(s);

        out.writeUTF("好的,,");

    }
}
