package com.TCP.heima.reverseString;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author lijie
 * @version 1.00
 * @Description: TODO
 * @date 2020/3/29 21:43
 */
public class Client {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("127.0.0.1", 54321);

        // 获取输入流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // 获取输出流
        PrintStream printStream = new PrintStream(socket.getOutputStream());

        // 将字符串写道服务器中
        printStream.println(scanner.nextLine());

        // 将反转后的结果读出来.
        System.out.println(bufferedReader.readLine());

        socket.close();

    }
}
