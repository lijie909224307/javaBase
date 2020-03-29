package com.TCP.inet;

import java.io.IOException;
import java.net.Socket;

/**
 * @author lijie
 * @version 1.00
 * @Description: 查询端口占用情况, 实质就是挨个连接,哪个端口打开了就会连接上.
 * http://www.manongjc.com/java_example/net_port.html
 * @date 2020/3/15 17:58
 */
public class PortUsedCheck {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        String host = "localhost";

        for (int i = 0; i < 1000; i++) {
            try {
                socket = new Socket(host,i);
                System.out.println("端口"+i+"已经被占用");
            } catch (IOException e) {
                System.out.println("Exception has occured " + e);
                continue;
            }
        }



    }
}
