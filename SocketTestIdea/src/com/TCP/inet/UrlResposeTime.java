package com.TCP.inet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lijie
 * @version 1.00
 * @Description: Java 获取 URL响应头的日期信息 > 访问时间
 * @date 2020/3/15 23:29
 */
public class UrlResposeTime {
    public static void main(String[] args) throws IOException {

        URL url = new URL("http://www.manongjc.com");
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

        long date = httpCon.getDate();
        if(date == 0){
            System.out.println("无法获取信息...");
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("Date : " + format.format(new Date(date)));
        }


    }
}
