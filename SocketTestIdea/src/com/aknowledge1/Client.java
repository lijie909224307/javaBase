package com.aknowledge1;

import java.io.*;
import java.net.Socket;

/**
 * @author lijie
 * @version 1.00
 * @Description: TODO
 * @date 2020-03-13 15:04
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5555);

        /*
        // local 相关是自身的 , port 代表连接的另一头,对于客户端来说即服务器.
        System.out.println("Just connect to : LocalAddress > " + socket.getLocalAddress());             // LocalAddress > /127.0.0.1
        System.out.println("Just connect to : LocalSocketAddress > " + socket.getLocalSocketAddress()); // LocalSocketAddress > /127.0.0.1:59721
        System.out.println("Just connect to : Port > " + socket.getPort());                             // Port > 5555
        System.out.println("Just connect to : LocalPort >" + socket.getLocalPort());                    // LocalPort > 59721
        */

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        DataInputStream in = new DataInputStream(inputStream);
        DataOutputStream out = new DataOutputStream(outputStream);

        System.out.println(in.readUTF());

        out.writeUTF("您好,我是客户端...");


    }
}
