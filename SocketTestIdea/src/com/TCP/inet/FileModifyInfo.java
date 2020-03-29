package com.TCP.inet;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lijie
 * @version 1.00
 * @Description: 查看主机指定文件最后修改时间
 * http://www.manongjc.com/java_example/net_filetime.html
 * @date 2020/3/15 18:52
 */
public class FileModifyInfo {
    public static void main(String[] args) throws IOException {

        URL url = new URL("http://d.hiphotos.baidu.com/zhidao/pic/item/6a63f6246b600c334c3e91cb1e4c510fd9f9a16a.jpg");
        URLConnection connection = url.openConnection();
        connection.setUseCaches(false);
        long timeStamp = connection.getLastModified();
        Date date = new Date(timeStamp);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("最后修改时间为 ： " + format.format(date));


    }
}
