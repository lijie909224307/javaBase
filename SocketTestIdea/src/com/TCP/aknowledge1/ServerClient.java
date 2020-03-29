package com.TCP.aknowledge1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lijie
 * @version 1.00
 * @Description: TODO
 * @date 2020/3/15 16:08
 */
public class ServerClient extends Thread{

    private ServerSocket serverSocket;

    /**
     * @description 重写构造方法
     * @author Lijie
     * @date 2020/3/15
     * @param port 端口号
     * @return
     */
    public ServerClient(int port) throws IOException {
        serverSocket = new ServerSocket(port);
//        serverSocket.setSoTimeout(5000);  // 设置超时时间
    }

    @Override
    public void run() {
        while (true){
            try {
                System.out.println("Waiting for client on port :" + serverSocket.getLocalPort() + "...");
                Socket socket = serverSocket.accept();

                /*
                // local 相关是自身的 , port 代表连接的另一头. 服务器为监听的端口信息.
                System.out.println("Just connect to : LocalAddress > " + socket.getLocalAddress());             // LocalAddress > /127.0.0.1
                System.out.println("Just connect to : LocalSocketAddress > " + socket.getLocalSocketAddress()); // LocalSocketAddress > /127.0.0.1:5555
                System.out.println("Just connect to : Port > " + socket.getPort());                             // Port > 59693
                System.out.println("Just connect to : LocalPort >" + socket.getLocalPort());                    // LocalPort >5555
                */

                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                // 向客户端发送消息
                dataOutputStream.writeUTF("你好,我是服务端...");

                // 接收客户端发送的消息
                System.out.println(dataInputStream.readUTF());



            }catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public static void main(String[] args) throws IOException {
        ServerClient serverClient = new ServerClient(5555);
        serverClient.start();


    }


}
