package com.TCP.heima.uploadFile;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lijie
 * @version 1.00
 * @Description: TODO
 * @date 2020/3/29 22:58
 */
public class UploadServer {
    public static void main(String[] args) throws IOException {

        // 3,创建多线程服务器
        ServerSocket server = new ServerSocket(12345);
        System.out.println("服务器启动,绑定12345端口");

        // 4.读取文件名
        while (true){
            Socket socket = server.accept();

            new Thread(){
                @Override
                public void run() {
                    try {
                        InputStream is = socket.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                        PrintStream printStream = new PrintStream(socket.getOutputStream());
                        String fileName = bufferedReader.readLine();

                        // 5,判断文件是否存在,将结果返回客户端
                        File dir = new File("upload");
                        dir.mkdir();
                        File file = new File(dir , fileName);   // 封装成File对象

                        if(file.exists()){  // 如果服务器已经存在这个问价, 返回 存在 .
                            printStream.println("存在");
                            socket.close();
                            return;
                        } else {
                            printStream.println("不存在");
                        }

                        // 8,定义FileOutputStream, 从网络读取数据,存储到本地
                        FileOutputStream fos = new FileOutputStream(file);
                        byte[] arr = new byte[8192];
                        int len;
                        while ((len = is.read(arr)) != -1){
                            fos.write(arr,0,len);
                        }
                        System.out.println("文件上传成功!");
                        fos.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }.start();
        }









    }
}
