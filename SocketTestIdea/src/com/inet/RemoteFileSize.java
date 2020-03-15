package com.inet;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author lijie
 * @version 1.00
 * @Description: 获取远程文件的大小
 * http://www.manongjc.com/java_example/net_serverfile.html
 * @date 2020/3/15 18:09
 */
public class RemoteFileSize {
    public static void main(String[] args) throws IOException {

        URL url = new URL("http://img.netbian.com/file/2016/1215/1f21c52b4e3ebd2543ffa482716044af.jpg");
        URLConnection connetion = url.openConnection();
        int size = connetion.getContentLength();
        if(size < 0){
            System.out.println("无法获取文件大小..");
        } else {
            System.out.println("文件大小为 : " + size);
        }
        connetion.getInputStream().close();
    }

}
