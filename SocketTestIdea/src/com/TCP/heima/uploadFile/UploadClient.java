package com.TCP.heima.uploadFile;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author lijie
 * @version 1.00
 * @Description: TODO
 * @date 2020/3/29 22:51
 */
public class UploadClient {
    public static void main(String[] args) throws IOException {

        // 1,提示输入要上传的文件路径,验证路径是否存在以及是否是文件夹
        File file = getFile();
        // 2,发送文件名到服务器
        Socket socket = new Socket("127.0.0.1", 12345);
        InputStream is = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream printStream = new PrintStream(socket.getOutputStream());
        printStream.println(file.getName());

        // 6,接收信息, 如果存在给予提示,程序直接退出
        String result = bufferedReader.readLine();
        if("存在".equals(result)){
            System.out.println("您上传的文件已经存在,请不要重复上传");
            socket.close();
            return;
        }
        // 7,如果不存在,定义FileInputStream 读取文件, 写出到服务器
        FileInputStream fis = new FileInputStream(file);
        byte[] arr = new byte[8192];
        int len;
        while ((len = fis.read(arr)) != -1){
            printStream.write(arr,0,len);
        }
        System.out.println("文件上传成功!");
        fis.close();
        socket.close();
    }

    private static File getFile() {
        // 创建键盘录入对象
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个文件路径:");
        while (true){
            String line = scanner.nextLine();
            File file = new File(line);

            if(!file.exists()){
                System.out.println("您录入的文件路径不存在,请重新输入:");
            } else if (file.isDirectory()){
                System.out.println("您录入的是文件夹路径,请输入一个文件路径:");
            }else {
                return file;
            }
        }
    }
}
