package com.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author lijie
 * @version 1.00
 * @Description:  简单服务端
 * @date 2019-08-27 9:39
 */
public class VerySimpleChatServer {

    ArrayList clientOutputStream;

    public class ClientHandler implements Runnable{

        BufferedReader reader;
        Socket sock;

        public ClientHandler(Socket clientSocket) {
            try {
                sock = clientSocket;
                InputStreamReader isr = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * @description 接收消息并发给所有客户端
         * @author Lijie
         * @date 2019-08-27
         */
        @Override
        public void run() {
            String msg ;
            try {
                while ((msg = reader.readLine()) != null){
                    System.out.println("read"+msg);
                    tellEveryone(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private void tellEveryone(String msg) {
        Iterator it = clientOutputStream.iterator();
        while (it.hasNext()){
            PrintWriter writer = (PrintWriter) it.next();
            writer.println(msg);
            writer.flush();
        }
    }

    public static void main(String[] args) {
        VerySimpleChatServer server = new VerySimpleChatServer();
        server.go();
    }

    private void go() {
        clientOutputStream = new ArrayList();
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            while (true){
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStream.add(writer);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("got a connection");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
