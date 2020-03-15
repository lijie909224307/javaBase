package com.inet;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lijie
 * @version 1.00
 * @Description: 多线程服务器  , 这里的 PrintStream ， 客户端可以用 BufferedReader 读取
 * http://www.manongjc.com/java_example/net_multisoc.html
 * @date 2020/3/15 18:19
 */
public class MultiThreadServer implements Runnable{
    private Socket csocket;

    public MultiThreadServer(Socket csocket) {
        this.csocket = csocket;
    }

    @Override
    public void run() {
        try {
            PrintStream pStream = new PrintStream(csocket.getOutputStream());
            for (int i = 5; i > 0; i--) {
                pStream.println(i + "bottles of beer on the wall");
            }
            Thread.sleep(2000);
            pStream.close();
            csocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("listening....");

        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("connected....");
            new Thread(new MultiThreadServer(socket)).start();
        }

    }


}
